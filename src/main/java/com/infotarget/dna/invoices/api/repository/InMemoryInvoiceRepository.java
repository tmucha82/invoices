package com.infotarget.dna.invoices.api.repository;

import com.infotarget.dna.invoices.api.model.Invoice;
import com.infotarget.dna.invoices.api.model.InvoiceId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryInvoiceRepository implements InvoiceRepository {

    private Map<InvoiceId, Invoice> repository = new ConcurrentHashMap<>();

    @Override
    public Invoice createNew(Invoice invoice) {
        return repository.put(invoice.getInvoiceId(), invoice);
    }
}
