package com.infotarget.dna.invoices.api.repository;

import com.infotarget.dna.invoices.api.model.Invoice;

public interface InvoiceRepository {

    Invoice createNew(Invoice invoice);
}
