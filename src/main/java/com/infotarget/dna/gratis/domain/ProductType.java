package com.infotarget.dna.gratis.domain;

import java.util.UUID;

public class ProductType {

    public static ProductType productTypeOf(UUID productId) {
        return new ProductType(ProductId.productIdOf(productId));
    }

    public static ProductType newProductType() {
        return new ProductType(ProductId.newOne());
    }

    private final ProductId productId;

    private ProductType(ProductId productId) {
        this.productId = productId;
    }

    public ProductId id() {
        return productId;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "productId=" + productId +
                '}';
    }
}
