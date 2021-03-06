package com.infotarget.dna.gratis.domain;

import java.util.Objects;
import java.util.UUID;

public class SerialNumber {
    static SerialNumber newOne() {
        return of(UUID.randomUUID());
    }

    public static SerialNumber of(UUID uuid) {
        return new SerialNumber(uuid);
    }

    private final UUID uuid;

    private SerialNumber(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID id() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerialNumber serialNumber = (SerialNumber) o;
        return Objects.equals(uuid, serialNumber.uuid);
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "uuid=" + uuid +
                '}';
    }
}
