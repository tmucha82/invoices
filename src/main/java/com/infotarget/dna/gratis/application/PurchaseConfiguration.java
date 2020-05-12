package com.infotarget.dna.gratis.application;

import com.infotarget.dna.gratis.domain.GratisPolicy;
import com.infotarget.dna.gratis.domain.ProductType;
import com.infotarget.dna.gratis.domain.PurchaseRepository;
import com.infotarget.dna.gratis.infrastructure.InMemoryPurchaseRepository;
import com.infotarget.dna.shared.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DomainEventPublisherConfiguration.class)
public class PurchaseConfiguration {

    private static ProductType SUBSCRIPTION = ProductType.newProductType();
    private static ProductType DDD_BOOK = ProductType.newProductType();

    @Bean
    GratisPolicy defaultGratisPolicy() {
        return GratisPolicy.oneGratisForProductType(SUBSCRIPTION, DDD_BOOK);
    }

    @Bean
    PurchaseService purchaseService(PurchaseRepository purchaseRepository, GratisPolicy gratisPolicy, DomainEventPublisher domainEventPublisher) {
        return new PurchaseService(purchaseRepository, gratisPolicy, domainEventPublisher);
    }

    @Bean
    PurchaseRepository inMemoryRepository() {
        return new InMemoryPurchaseRepository();
    }
}
