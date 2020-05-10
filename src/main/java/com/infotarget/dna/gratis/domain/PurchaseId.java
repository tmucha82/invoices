package com.infotarget.dna.gratis.domain;

import java.util.Objects;
import java.util.UUID;

public class PurchaseId {
    private final UUID id;

    public static PurchaseId newOne() {
        return new PurchaseId(UUID.randomUUID());
    }

    private PurchaseId(UUID id) {
        this.id = id;
    }

    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseId that = (PurchaseId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "PurchaseId{" +
                "id=" + id +
                '}';
    }
}
