package com.infotarget.dna.gratis.infrastructure;

import com.infotarget.dna.gratis.domain.Purchase;
import com.infotarget.dna.gratis.domain.PurchaseId;
import com.infotarget.dna.gratis.domain.PurchaseRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPurchaseRepository implements PurchaseRepository {
    private final Map<PurchaseId, Purchase> repository = new ConcurrentHashMap<>();

    @Override
    public Purchase createNew(Purchase purchase) {
        return update(purchase);
    }

    @Override
    public Purchase update(Purchase purchase) {
        repository.put(purchase.id(), purchase);
        return purchase;
    }

    @Override
    public Optional<Purchase> findBy(PurchaseId id) {
        Objects.requireNonNull(id);
        return repository.values().stream()
                .filter(purchase -> id.equals(purchase.id()))
                .findFirst();
    }
}
