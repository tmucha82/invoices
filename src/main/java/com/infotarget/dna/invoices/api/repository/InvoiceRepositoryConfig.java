package com.infotarget.dna.invoices.api.repository;

import org.springframework.context.annotation.Bean;

public class InvoiceRepositoryConfig {

    @Bean
    InvoiceRepository invoiceRepository() {
        return new InMemoryInvoiceRepository();
    }

}
