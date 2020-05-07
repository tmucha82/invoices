package com.infotarget.dna.invoices.api.service;

import com.infotarget.dna.invoices.api.repository.InvoiceRepository;
import com.infotarget.dna.invoices.api.repository.InvoiceRepositoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InvoiceRepositoryConfig.class)
public class ReceiveInvoiceConfig {

    @Bean
    ReceiveInvoiceService receiveInvoiceService(InvoiceRepository invoiceRepository) {
        return new ReceiveInvoiceService(invoiceRepository);
    }
}
