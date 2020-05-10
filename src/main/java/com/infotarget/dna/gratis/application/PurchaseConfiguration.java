package com.infotarget.dna.gratis.application;

import com.infotarget.dna.gratis.domain.PurchaseRepository;
import com.infotarget.dna.gratis.infrastructure.InMemoryPurchaseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseConfiguration {

    @Bean
    PurchaseService purchaseService(PurchaseRepository purchaseRepository) {
        return new PurchaseService(purchaseRepository);
    }

    @Bean
    PurchaseRepository inMemoryRepository() {
        return new InMemoryPurchaseRepository();
    }
}
