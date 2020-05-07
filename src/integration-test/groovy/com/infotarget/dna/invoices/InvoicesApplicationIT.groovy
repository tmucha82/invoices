package com.infotarget.dna.invoices

import com.infotarget.dna.invoices.api.controller.InvoiceController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class InvoicesApplicationIT extends Specification {
    @Autowired(required = false)
    private InvoiceController invoiceController

    def 'Application should be started'() {
        expect:
        invoiceController
    }
}