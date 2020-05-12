package com.infotarget.dna.gratis.application

import com.infotarget.dna.gratis.domain.*
import com.infotarget.dna.gratis.infrastructure.InMemoryPurchaseRepository
import com.infotarget.dna.shared.DomainEvent
import com.infotarget.dna.shared.Result
import spock.lang.Specification

import static java.util.Map.of

class PurchaseServiceSpec extends Specification {

    private static ProductType REGULAR = ProductType.newProductType()
    private static ProductType PROMOTED = ProductType.newProductType()
    private static ProductType GRATIS = ProductType.newProductType()

    private PurchaseRepository purchaseRepository
    private PurchaseService purchaseService

    def setup() {
        purchaseRepository = new InMemoryPurchaseRepository()
        purchaseService = new PurchaseService(
                purchaseRepository,
                GratisPolicy.oneGratisForProductType(PROMOTED, GRATIS),
                new LogDomainEventPublisher())
    }

    def 'Should add product without gratis for regular product'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product regularProduct = Product.productOfType(REGULAR)
        when:
            Result result = purchaseService.addProduct(purchase.id(), regularProduct)
        then:
            result.isSuccessful()
            result.events().size() == 1
            assertEvent(result.events().last(), regularProduct)
            purchase.order() == Order.of(purchase.id(), of(REGULAR, 1L))
    }

    def 'Should remove product without gratis for regular product'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product regularProduct = Product.productOfType(REGULAR)
        and:
            purchaseService.addProduct(purchase.id(), regularProduct).isSuccessful()
        when:
            Result result = purchaseService.removeProduct(purchase.id(), regularProduct)
        then:
            result.isSuccessful()
            result.events().size() == 1
            assertEvent(result.events().last(), regularProduct)
            purchase.order() == Order.empty(purchase.id())
    }

    def 'Should failed when removing regular product that was not added'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        when:
            Result result = purchaseService.removeProduct(purchase.id(), Product.productOfType(REGULAR))
        then:
            result.isFailure()
            result.reason().contains("Cannot remove product")
    }

    def 'Should failed when adding regular product that was already added'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product product = Product.productOfType(REGULAR)
        and:
            purchaseService.addProduct(purchase.id(), product).isSuccessful()
        when:
            Result result = purchaseService.addProduct(purchase.id(), product)
        then:
            result.isFailure()
            result.reason().contains("Cannot add product")
    }

    def 'Should add promoted product with extra gratis product'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product promotedProduct = Product.productOfType(PROMOTED)
        when:
            Result result = purchaseService.addProduct(purchase.id(), promotedProduct)
        then:
            result.isSuccessful()
            result.events().size() == 2
            purchase.order() == Order.of(purchase.id(), of(PROMOTED, 1L), of(GRATIS, 1L))
    }

    def 'Should remove promoted product with extra gratis product'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product promotedProduct = Product.productOfType(PROMOTED)
        and:
            purchaseService.addProduct(purchase.id(), promotedProduct).isSuccessful()
        when:
            Result result = purchaseService.removeProduct(purchase.id(), promotedProduct)
        then:
            result.isSuccessful()
            result.events().size() == 2
            purchase.order() == Order.empty(purchase.id())
    }

    def 'Should remove gratis product related to promoted one'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product promotedProduct = Product.productOfType(PROMOTED)
        and:
            Result addedResult = purchaseService.addProduct(purchase.id(), promotedProduct)
        and:
            Product addedGratis = getAddedProductFromEvent(addedResult, GratisProductAdded.class)
        when:
            Result result = purchaseService.removeGratisOnDemand(purchase.id(), addedGratis)
        then:
            result.isSuccessful()
            result.events().size() == 1
            assertEvent(result.events().last(), addedGratis)
            purchase.order() == Order.of(purchase.id(), of(PROMOTED, 1L))
    }

    def 'Should add once again gratis product related to promoted one'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product promotedProduct = Product.productOfType(PROMOTED)
        and:
            Result addedResult = purchaseService.addProduct(purchase.id(), promotedProduct)
        and:
            Product addedGratis = getAddedProductFromEvent(addedResult, GratisProductAdded.class)
        and:
            purchaseService.removeGratisOnDemand(purchase.id(), addedGratis).isSuccessful()
        when:
            Result result = purchaseService.addGratisAgain(purchase.id(), addedGratis)
        then:
            result.isSuccessful()
            result.events().size() == 1
            assertEvent(result.events().last(), addedGratis)
            purchase.order() == Order.of(purchase.id(), of(PROMOTED, 1L), of(GRATIS, 1L))
    }

    def 'Should not add once again gratis that was not removed'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product promotedProduct = ProductFixture.productOfType(PROMOTED)
        and:
            Result addedResult = purchaseService.addProduct(purchase.id(), promotedProduct)
            addedResult.isSuccessful()
        and:
            Product addedGratis = getAddedProductFromEvent(addedResult, GratisProductAdded.class)
        when:
            Result result = purchaseService.addGratisAgain(purchase.id(), addedGratis)
        then:
            result.isFailure()
            result.reason().contains("Cannot add gratis")
            purchase.order() == Order.of(purchase.id(), of(PROMOTED, 1L), of(GRATIS, 1L))
    }

    def 'Should not remove gratis that was not ever added'() {
        given:
            Purchase purchase = purchaseRepository.createNew(Purchase.newOne())
        and:
            Product regularProduct = ProductFixture.regularProduct()
        and:
            Result addedResult = purchaseService.addProduct(purchase.id(), regularProduct)
            addedResult.isSuccessful()
        when:
            Result result = purchaseService.removeGratisOnDemand(purchase.id(), Product.productOfType(GRATIS))
        then:
            result.isFailure()
            result.reason().contains("Cannot remove gratis")
            purchase.order() == Order.of(purchase.id(), of(regularProduct.getProductType(), 1L))
    }

    static def assertEvent(DomainEvent event, Product product) {
        ProductEvent productEvent = (ProductEvent) event
        productEvent.productId() == product.getSerialNumber().id()
    }

    static <T> Product getAddedProductFromEvent(Result addedResult, Class<T> aClass) {
        GratisProductAdded gratisEvent = addedResult.events().stream()
                .filter(p -> aClass == p.getClass())
                .map(ev -> (GratisProductAdded) ev)
                .findFirst().orElseThrow(IllegalStateException::new)
        Product.of(SerialNumber.of(gratisEvent.productId()), GRATIS)
    }
}
