package com.infotarget.dna.gratis.domain

import com.infotarget.dna.shared.DomainEvent
import com.infotarget.dna.shared.Result
import spock.lang.Specification

import static java.util.Map.*

class PurchaseSpec extends Specification {

    public static ProductType promotedType = ProductTypeFixture.anyType()
    public static ProductType gratisType = ProductTypeFixture.anyType()
    public static GratisPolicy gratisPolicy = GratisPolicy.oneGratisForProductType(promotedType, gratisType)

    Purchase purchase = PurchaseFixture.newPurchase()

    def 'Should add product without gratis for regular product'() {
        given:
            Product regularProduct = ProductFixture.regularProduct()
        expect:
            purchase.addProduct(regularProduct, gratisPolicy).isSuccessful()
            purchase.order() == Order.of(purchase.id(), of(regularProduct.getProductType(), 1L))
    }

    def 'Should remove product without gratis for regular product'() {
        given:
            Product regularProduct = ProductFixture.regularProduct()
        and:
            purchase.addProduct(regularProduct, gratisPolicy).isSuccessful()
        when:
            Result result = purchase.removeProduct(regularProduct, gratisPolicy)
        then:
            result.isSuccessful()
            purchase.order() == Order.empty(purchase.id())
    }

    def 'Should failed when removing regular product that was not added'() {
        given:
            Product notAdded = ProductFixture.regularProduct()
        when:
            Result result = purchase.removeProduct(notAdded, gratisPolicy)
        then:
            result.isFailure()
            result.reason().contains("Cannot remove product")
    }

    def 'Should failed when adding regular product that was already added'() {
        given:
            Product alreadyAdded = ProductFixture.regularProduct()
        and:
            purchase.addProduct(alreadyAdded, gratisPolicy).isSuccessful()
        when:
            Result result = purchase.addProduct(alreadyAdded, gratisPolicy)
        then:
            result.isFailure()
            result.reason().contains("Cannot add product")
    }

    def 'Should add promoted product with extra gratis product'() {
        given:
            Product promotedProduct = ProductFixture.productOfType(promotedType)
        when:
            Result result = purchase.addProduct(promotedProduct, gratisPolicy)
        then:
            result.isSuccessful()
            purchase.order() == Order.of(purchase.id(),
                    of(promotedType, 1L),
                    of(gratisType, 1L))
    }

    def 'Should remove promoted product with extra gratis product'() {
        given:
            Product promotedProduct = ProductFixture.productOfType(promotedType)
        and:
            purchase.addProduct(promotedProduct, gratisPolicy).isSuccessful()
        when:
            Result result = purchase.removeProduct(promotedProduct, gratisPolicy)
        then:
            result.isSuccessful()
            purchase.order() == Order.empty(purchase.id())
    }

    def 'Should remove gratis product related to promoted one'() {
        given:
            Product promotedProduct = ProductFixture.productOfType(promotedType)
        and:
            Result addedResult = purchase.addProduct(promotedProduct, gratisPolicy)
            addedResult.isSuccessful()
        and:
            GratisProductAdded gratisEvent = addedResult.events().stream()
                    .filter(p -> p instanceof GratisProductAdded)
                    .map(ev -> (GratisProductAdded) ev)
                    .findFirst().orElseThrow(IllegalStateException::new)
        when:
            Result result = purchase.removeGratis(Product.of(gratisEvent.getSerialNumber(), gratisType));
        then:
            result.isSuccessful()
            purchase.order() == Order.of(purchase.id(), of(promotedType, 1L))
    }

    def 'Should add once again gratis product related to promoted one'() {
//        when:
//            purchase.addGratisAgain();
    }

    def 'Should not add once again gratis that was not removed'() {
    }

    def 'Should not remove gratis that was not ever added'() {
    }
}
