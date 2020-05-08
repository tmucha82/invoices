package com.infotarget.dna.gratis.domain;

import com.infotarget.dna.shared.Result;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private final List<Product> ordered;

    public Purchase() {
        ordered = new ArrayList<>();
    }

    Result addProduct(Product product) {
        if (alreadyOrdered(product)) {
            return Result.failure("Cannot add product");
        }
        ordered.add(product);
        return Result.success();
    }

    Result removeProduct(Product product) {
        if (notOrderedYet(product)) {
            return Result.failure("Cannot remove product");
        }
        ordered.remove(product);
        return Result.success();
    }

    private boolean alreadyOrdered(Product product) {
        return ordered.contains(product);
    }

    private boolean notOrderedYet(Product product) {
        return !alreadyOrdered(product);
    }

}
