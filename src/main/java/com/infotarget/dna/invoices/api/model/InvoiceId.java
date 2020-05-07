package com.infotarget.dna.invoices.api.model;

import java.util.Objects;
import java.util.UUID;

public class InvoiceId {

    private final UUID id;

    private InvoiceId(UUID id) {
        this.id = id;
    }

    public static InvoiceId newOne() {
        return new InvoiceId(UUID.randomUUID());
    }

    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceId that = (InvoiceId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "InvoiceId{" +
                "id=" + id +
                '}';
    }
}
