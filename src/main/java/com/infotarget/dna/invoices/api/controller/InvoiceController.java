package com.infotarget.dna.invoices.api.controller;

import com.infotarget.dna.invoices.api.model.Invoice;
import com.infotarget.dna.invoices.api.service.ReceiveInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import static com.infotarget.dna.invoices.api.model.Invoice.createInvoice;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ReceiveInvoiceService receiveInvoiceService;

    public InvoiceController(ReceiveInvoiceService receiveInvoiceService) {
        this.receiveInvoiceService = receiveInvoiceService;
    }

    @PutMapping
    ResponseEntity<Map<String, Object>> uploadInvoice(@RequestBody UploadInvoiceRequest uploadInvoiceRequest) {
        LOG.info("Uploading invoice with data = {}", uploadInvoiceRequest);
        Invoice invoice = receiveInvoiceService.upload(createInvoice());
        LOG.info("Invoice was successfully uploaded = {}", invoice);
        return ResponseEntity.accepted()
                .build();
    }

    @Profile("dev")
    @GetMapping
    ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Ok");
    }
}
