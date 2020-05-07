package com.infotarget.dna.invoices.api.service;

import com.infotarget.dna.invoices.api.model.Invoice;
import com.infotarget.dna.invoices.api.repository.InvoiceRepository;

public class ReceiveInvoiceService {

    private final InvoiceRepository invoiceRepository;

    public ReceiveInvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice upload(Invoice invoice) {
        return invoiceRepository.createNew(invoice);
    }
}
