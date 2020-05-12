package com.infotarget.dna.gratis.application;

import com.infotarget.dna.gratis.domain.GratisPolicy;
import com.infotarget.dna.gratis.domain.Product;
import com.infotarget.dna.gratis.domain.Purchase;
import com.infotarget.dna.gratis.domain.PurchaseId;
import com.infotarget.dna.gratis.domain.PurchaseRepository;
import com.infotarget.dna.shared.DomainEventPublisher;
import com.infotarget.dna.shared.Result;
import io.vavr.Function2;
import io.vavr.Function3;

import static java.lang.String.format;

public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final GratisPolicy gratisPolicy;
    private final DomainEventPublisher domainEventPublisher;

    PurchaseService(PurchaseRepository purchaseRepository, GratisPolicy gratisPolicy, DomainEventPublisher domainEventPublisher) {
        this.purchaseRepository = purchaseRepository;
        this.gratisPolicy = gratisPolicy;
        this.domainEventPublisher = domainEventPublisher;
    }

    public Result addProduct(PurchaseId purchaseId, Product product) {
        return execute(purchaseId, product, Purchase::addProduct);
    }

    public Result removeProduct(PurchaseId purchaseId, Product product) {
        return execute(purchaseId, product, Purchase::removeProduct);
    }

    public Result removeGratisOnDemand(PurchaseId purchaseId, Product product) {
        return execute(purchaseId, product, Purchase::removeGratis);
    }

    public Result addGratisAgain(PurchaseId purchaseId, Product product) {
        return execute(purchaseId, product, Purchase::addGratisAgain);
    }

    private  Result execute(PurchaseId purchaseId, Product product, Function2<Purchase, Product, Result> supplier) {
        return execute(purchaseId, product, (pu, pr, gr) -> supplier.apply(pu, pr));
    }

    private  Result execute(PurchaseId purchaseId, Product product, Function3<Purchase, Product, GratisPolicy, Result> operation) {
        Purchase purchase = purchaseRepository.findBy(purchaseId).orElse(null);
        if (purchase == null) {
            return Result.failure(format("Cannot find purchase with id = %s", purchaseId));
        }
        Result result = operation.apply(purchase, product, gratisPolicy);
        result.events().forEach(domainEventPublisher::publish);
        purchaseRepository.update(purchase);
        return result;
    }
}
