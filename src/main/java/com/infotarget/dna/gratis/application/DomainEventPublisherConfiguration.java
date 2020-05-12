package com.infotarget.dna.gratis.application;

import com.infotarget.dna.shared.DomainEvent;
import com.infotarget.dna.shared.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.invoke.MethodHandles;

@Configuration
class DomainEventPublisherConfiguration {

    @Bean
    DomainEventPublisher domainEventPublisher() {
        return new NoOpsDomainEventPublisher();
    }
}

class NoOpsDomainEventPublisher implements DomainEventPublisher {

    @Override
    public void publish(DomainEvent event) {
    }
}

class LogDomainEventPublisher implements DomainEventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void publish(DomainEvent event) {
        LOG.info("Published event: {}", event);
    }
}