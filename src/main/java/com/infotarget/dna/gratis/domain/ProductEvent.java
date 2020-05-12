package com.infotarget.dna.gratis.domain;

import com.infotarget.dna.shared.DomainEvent;

import java.util.UUID;

public interface ProductEvent extends DomainEvent {
    UUID productId();
}
