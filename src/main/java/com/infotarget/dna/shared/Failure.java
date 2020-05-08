package com.infotarget.dna.shared;

import java.util.Objects;

class Failure {

    private final String reason;

    Failure(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Failure failure = (Failure) o;
        return Objects.equals(reason, failure.reason);
    }

    @Override
    public String toString() {
        return "Failure{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
