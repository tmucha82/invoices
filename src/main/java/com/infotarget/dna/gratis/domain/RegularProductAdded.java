package com.infotarget.dna.gratis.domain;

import com.infotarget.dna.shared.DomainEvent;

import java.util.Objects;
import java.util.UUID;

public class RegularProductAdded implements DomainEvent {
    private final UUID eventId;
    private final PurchaseId purchaseId;
    private final SerialNumber serialNumber;

    RegularProductAdded(PurchaseId purchaseId, SerialNumber serialNumber) {
        this.eventId = UUID.randomUUID();
        this.purchaseId = purchaseId;
        this.serialNumber = serialNumber;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    @Override
    public UUID eventId() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegularProductAdded that = (RegularProductAdded) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public String toString() {
        return "RegularProductAdded{" +
                "eventId=" + eventId +
                ", purchaseId=" + purchaseId +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
