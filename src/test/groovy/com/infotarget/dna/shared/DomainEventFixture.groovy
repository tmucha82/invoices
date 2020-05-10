package com.infotarget.dna.shared

class DomainEventFixture {
    static DomainEvent randomEvent() {
        RandomDomainEvent.randomEvent()
    }
}

class RandomDomainEvent implements DomainEvent {
    static DomainEvent randomEvent() {
        new RandomDomainEvent(UUID.randomUUID())
    }

    private final UUID eventId

    private RandomDomainEvent(UUID eventId) {
        this.eventId = eventId
    }

    @Override
    UUID eventId() {
        eventId
    }
}
