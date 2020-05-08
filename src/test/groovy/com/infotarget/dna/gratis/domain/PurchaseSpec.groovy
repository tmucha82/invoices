package com.infotarget.dna.gratis.domain

import com.infotarget.dna.shared.Result
import spock.lang.Specification

class PurchaseSpec extends Specification {

    Purchase purchase = PurchaseFixture.aPurchase()

    def 'Should add product without gratis for regular product'() {
        given:
            Product regularProduct = ProductFixture.regularProduct()
        expect:
            purchase.addProduct(regularProduct).isSuccessful()
    }

    def 'Should remove product without gratis for regular product'() {
        given:
            Product regularProduct = ProductFixture.regularProduct()
        and:
            purchase.addProduct(regularProduct)
        when:
            Result result = purchase.removeProduct(regularProduct)
        then:
            result.isSuccessful()
    }

    def 'Should failed when removing regular product that was not added'() {
        given:
            Product notAdded = ProductFixture.regularProduct()
        when:
            Result result = purchase.removeProduct(notAdded)
        then:
            result.isFailure()
            result.reason().contains("Cannot remove product")
    }

    def 'Should failed when adding regular product that was already added'() {
        given:
            Product alreadyAdded = ProductFixture.regularProduct()
        and:
            purchase.addProduct(alreadyAdded).isSuccessful()
        when:
            Result result = purchase.addProduct(alreadyAdded)
        then:
            result.isFailure()
            result.reason().contains("Cannot add product")
    }

    def 'Should add promoted product with extra gratis product'() {

    }

    def 'Should remove promoted product with extra gratis product'() {

    }

    def 'Should remove gratis product related to promoted one'() {

    }

    def 'Should add once again gratis product related to promoted one'() {

    }

}
