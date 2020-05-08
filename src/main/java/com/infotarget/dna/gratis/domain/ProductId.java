package com.infotarget.dna.gratis.domain;

import java.util.Objects;
import java.util.UUID;

public class ProductId<T> {

    static ProductId<UUID> newOne() {
        return productIdOf(UUID.randomUUID());
    }

    static <T> ProductId<T> productIdOf(T productIdentifier) {
        return new ProductId<T>(productIdentifier);
    }

    private final T uniqueIdentifier;

    private ProductId(T uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public T id() {
        return uniqueIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(uniqueIdentifier, productId.uniqueIdentifier);
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "uniqueIdentifier='" + uniqueIdentifier + '\'' +
                '}';
    }
}
