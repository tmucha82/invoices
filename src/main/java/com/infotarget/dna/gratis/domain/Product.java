package com.infotarget.dna.gratis.domain;

import java.util.Objects;

public class Product {

    public static Product of(SerialNumber serialNumber, ProductType productType) {
        return new Product(serialNumber, productType);
    }
    public static Product productOfType(ProductType productType) {
        return of(SerialNumber.newOne(), productType);
    }

    private final SerialNumber serialNumber;
    private final ProductType productType;

    private Product(SerialNumber serialNumber, ProductType productType) {
        this.serialNumber = serialNumber;
        this.productType = productType;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public ProductType getProductType() {
        return productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(serialNumber, product.serialNumber) &&
                Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + serialNumber +
                ", productType=" + productType +
                '}';
    }
}
