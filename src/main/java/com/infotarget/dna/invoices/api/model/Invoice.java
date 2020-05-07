package com.infotarget.dna.invoices.api.model;

import static java.time.Clock.systemDefaultZone;

public class Invoice {
    public static Invoice createInvoice() {
        return new Invoice();
    }

    private final InvoiceId invoiceId;

    public Invoice() {
        invoiceId = InvoiceId.newOne();
    }

    public InvoiceId getInvoiceId() {
        return invoiceId;
    }
}
