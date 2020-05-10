package com.infotarget.dna.gratis.domain

class ProductFixture {
    static Product regularProduct() {
        Product.productOfType(ProductType.newProductType())
    }

    static Product productOfType(ProductType productType) {
        Product.productOfType(productType)
    }
}
