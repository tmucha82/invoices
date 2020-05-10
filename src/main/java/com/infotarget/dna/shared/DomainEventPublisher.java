package com.infotarget.dna.shared;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
