package com.infotarget.dna.gratis.application;

import com.infotarget.dna.gratis.domain.GratisPolicy;
import com.infotarget.dna.gratis.domain.Product;
import com.infotarget.dna.gratis.domain.Purchase;
import com.infotarget.dna.gratis.domain.PurchaseId;
import com.infotarget.dna.gratis.domain.PurchaseRepository;
import com.infotarget.dna.shared.DomainEventPublisher;
import com.infotarget.dna.shared.Result;

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
        Purchase purchase = purchaseRepository.findBy(purchaseId).orElse(null);
        if (purchase == null) {
            return Result.failure(format("Cannot find purchase with id = %s", purchaseId));
        }
        Result result = purchase.addProduct(product, gratisPolicy);
        result.events().forEach(domainEventPublisher::publish);
        purchaseRepository.update(purchase);
        return result;
    }

    public Result removeProduct(PurchaseId purchaseId, Product product) {
        Purchase purchase = purchaseRepository.findBy(purchaseId).orElse(null);
        if (purchase == null) {
            return Result.failure(format("Cannot find purchase with id = %s", purchaseId));
        }
        Result result = purchase.removeProduct(product, gratisPolicy);
        result.events().forEach(domainEventPublisher::publish);
        purchaseRepository.update(purchase);
        return result;
    }

    public Result removeGratisOnDemand(PurchaseId purchaseId, Product product) {
        Purchase purchase = purchaseRepository.findBy(purchaseId).orElse(null);
        if (purchase == null) {
            return Result.failure(format("Cannot find purchase with id = %s", purchaseId));
        }
        Result result = purchase.removeGratis(product);
        result.events().forEach(domainEventPublisher::publish);
        purchaseRepository.update(purchase);
        return result;
    }

    public Result addGratisAgain(PurchaseId purchaseId, Product product) {
        Purchase purchase = purchaseRepository.findBy(purchaseId).orElse(null);
        if (purchase == null) {
            return Result.failure(format("Cannot find purchase with id = %s", purchaseId));
        }
        Result result = purchase.addGratisAgain(product);
        result.events().forEach(domainEventPublisher::publish);
        purchaseRepository.update(purchase);
        return result;
    }
}
