package com.infotarget.dna.gratis.domain;

import java.util.Map;
import java.util.Objects;

public class Order {

    public static Order empty(PurchaseId purchaseId) {
        return of(purchaseId, Map.of());
    }

    public static Order of(PurchaseId purchaseId, Map<ProductType, Long> items) {
        return of(purchaseId, items, Map.of());
    }

    public static Order of(PurchaseId purchaseId, Map<ProductType, Long> items, Map<ProductType, Long> freeItems) {
        return new Order(purchaseId, items, freeItems);
    }

    private final PurchaseId purchaseId;
    private final Map<ProductType, Long> items;
    private final Map<ProductType, Long> freeItems;

    private Order(PurchaseId purchaseId, Map<ProductType, Long> items, Map<ProductType, Long> freeItems) {
        this.purchaseId = purchaseId;
        this.items = items;
        this.freeItems = freeItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(purchaseId, order.purchaseId) &&
                Objects.equals(items, order.items) &&
                Objects.equals(freeItems, order.freeItems);
    }

    @Override
    public String toString() {
        return "Order{" +
                "purchaseId=" + purchaseId +
                ", items=" + items +
                ", freeItems=" + freeItems +
                '}';
    }
}
