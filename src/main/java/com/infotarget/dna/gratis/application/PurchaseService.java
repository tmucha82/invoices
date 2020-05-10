package com.infotarget.dna.gratis.application;

import com.infotarget.dna.gratis.domain.PurchaseRepository;

public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
}
