package com.infotarget.dna.gratis.domain;

import java.util.Optional;

public interface PurchaseRepository {

    Purchase createNew(Purchase purchase);

    Purchase update(Purchase sub);

    Optional<Purchase> findBy(PurchaseId id);

}
