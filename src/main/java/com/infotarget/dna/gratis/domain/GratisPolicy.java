package com.infotarget.dna.gratis.domain;

import java.util.List;
import java.util.Map;

public interface GratisPolicy {
    static GratisPolicy oneGratisForProductType(ProductType promotedType, ProductType gratisType) {
        return new OneGratisForProductTypes(Map.of(promotedType, gratisType));
    }

    static GratisPolicy noGratis() {
        return new NoGratisPolicy();
    }

    List<ProductType> gratisProducts(Purchase purchase, Product product);
}

class NoGratisPolicy implements GratisPolicy {

    @Override
    public List<ProductType> gratisProducts(Purchase purchase, Product product) {
        return List.of();
    }
}

class OneGratisForProductTypes implements GratisPolicy {

    private final Map<ProductType, ProductType> promotedTypesAndGratis;

    OneGratisForProductTypes(Map<ProductType, ProductType> promotedTypesAndGratis) {
        this.promotedTypesAndGratis = promotedTypesAndGratis;
    }

    @Override
    public List<ProductType> gratisProducts(Purchase purchase, Product product) {
        if (isPromotedType(product.getProductType())) {
            ProductType gratisType = promotedTypesAndGratis.get(product.getProductType());
            return List.of(gratisType);
        }
        return List.of();
    }

    private boolean isPromotedType(ProductType productType) {
        return promotedTypesAndGratis.containsKey(productType);
    }
}


