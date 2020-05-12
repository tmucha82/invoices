package com.infotarget.dna.gratis.domain;


import java.util.Objects;
import java.util.UUID;

public class GratisProductRemoved implements ProductEvent {

    private final UUID eventId;
    private final PurchaseId purchaseId;
    private final SerialNumber serialNumber;

    GratisProductRemoved(PurchaseId purchaseId, SerialNumber serialNumber) {
        this.eventId = UUID.randomUUID();
        this.purchaseId = purchaseId;
        this.serialNumber = serialNumber;
    }

    @Override
    public UUID productId() {
        return serialNumber.id();
    }

    @Override
    public UUID eventId() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GratisProductRemoved that = (GratisProductRemoved) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public String toString() {
        return "GratisProductRemoved{" +
                "eventId=" + eventId +
                ", purchaseId=" + purchaseId +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
