package com.infotarget.dna.shared;

import java.util.List;

class Success {

    private final List<DomainEvent> events;

    Success(List<DomainEvent> events) {
        this.events = events;
    }

    List<DomainEvent> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Success{}";
    }
}
