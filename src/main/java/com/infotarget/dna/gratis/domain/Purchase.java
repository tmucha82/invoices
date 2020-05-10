package com.infotarget.dna.gratis.domain;

import com.infotarget.dna.shared.DomainEvent;
import com.infotarget.dna.shared.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Purchase {
    public static Purchase newOne() {
        return new Purchase(PurchaseId.newOne());
    }

    private final PurchaseId purchaseId;
    private final List<Product> ordered;
    private final List<Product> freeProducts;
    private final List<Product> removedOnDemand;

    private Purchase(PurchaseId purchaseId) {
        this.purchaseId = purchaseId;
        ordered = new ArrayList<>();
        freeProducts = new ArrayList<>();
        removedOnDemand = new ArrayList<>();
    }

    public Result addProduct(Product product, GratisPolicy gratisPolicy) {
        if (alreadyOrdered(product)) {
            return Result.failure("Cannot add product");
        }
        ordered.add(product);
        List<Product> extraProducts = gratisPolicy.gratisProducts(this, product).stream()
                .map(Product::productOfType)
                .collect(Collectors.toList());
        freeProducts.addAll(extraProducts);
        return Result.success(createAddedEvents(product, extraProducts));
    }

    public Result removeProduct(Product product, GratisPolicy gratisPolicy) {
        if (notOrderedYet(product)) {
            return Result.failure("Cannot remove product");
        }
        ordered.remove(product);
        gratisPolicy.gratisProducts(this, product)
                .forEach(gratisType -> {
                    Product productToRemove = freeProducts.stream()
                            .filter(p -> p.getProductType().equals(gratisType))
                            .findFirst()
                            .orElse(Product.productOfType(gratisType));
                    freeProducts.remove(productToRemove);
                });
        return Result.success();//TODO
    }

    public Result addGratisAgain(Product product) {
        if (wasRemovedOnDemand(product)) {
            freeProducts.add(product);
            return Result.success();//TODO
        }
        return Result.failure("Cannot add gratis again since it was not removed");
    }

    public Result removeGratis(Product product) {
        if (freeProducts.contains(product)) {
            freeProducts.remove(product);
            removedOnDemand.add(product);
            return Result.success();//TODO
        }
        return Result.failure("Cannot remove gratis that was not added");
    }

    public PurchaseId id() {
        return purchaseId;
    }

    public Order order() {
        return Order.of(purchaseId,
                ordered.stream()
                        .collect(Collectors
                                .groupingBy(Product::getProductType, Collectors.counting())),
                freeProducts.stream()
                        .collect(Collectors
                                .groupingBy(Product::getProductType, Collectors.counting())));
    }

    private List<DomainEvent> createAddedEvents(Product regularProduct, List<Product> extraProducts) {
        List<DomainEvent> result = extraProducts.stream()
                .map(gratis -> new GratisProductAdded(purchaseId, gratis.getSerialNumber()))
                .collect(Collectors.toList());
        result.add(new RegularProductAdded(purchaseId, regularProduct.getSerialNumber()));
        return result;
    }

    private boolean wasRemovedOnDemand(Product product) {
        return removedOnDemand.contains(product);
    }

    private boolean alreadyOrdered(Product product) {
        return ordered.contains(product);
    }

    private boolean notOrderedYet(Product product) {
        return !alreadyOrdered(product);
    }

}
