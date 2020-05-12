package com.infotarget.dna.gratis.domain;

import com.infotarget.dna.shared.DomainEvent;

import java.util.Objects;
import java.util.UUID;

public class GratisProductAdded implements ProductEvent {
    private final UUID eventId;
    private final PurchaseId purchaseId;
    private final SerialNumber serialNumber;

    GratisProductAdded(PurchaseId purchaseId, SerialNumber serialNumber) {
        this.eventId = UUID.randomUUID();
        this.purchaseId = purchaseId;
        this.serialNumber = serialNumber;
    }

    @Override
    public UUID eventId() {
        return eventId;
    }

    @Override
    public UUID productId() {
        return serialNumber.id();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GratisProductAdded that = (GratisProductAdded) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public String toString() {
        return "GratisProductAdded{" +
                "eventId=" + eventId +
                ", purchaseId=" + purchaseId +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
