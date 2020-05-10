package com.infotarget.dna.shared

import spock.lang.Specification

class ResultSpec extends Specification {

    def 'Result success should be successful'() {
        expect:
            Result.success().isSuccessful()
    }

    def 'Result success should not be failure'() {
        expect:
            !Result.success().isFailure()
    }

    def 'Result failure should not be successful'() {
        expect:
            Result.failure().isFailure()
    }

    def 'Result failure should be failure'() {
        expect:
            !Result.failure().isSuccessful()
    }

    def 'Failure should contain reason'() {
        given:
            String reason = "Reason of failure"
        when:
            Result result = Result.failure(reason)
        then:
            result.isFailure()
            result.reason() == reason
    }

    def 'Success should return OK reason'() {
        when:
            Result result = Result.success()
        then:
            result.isSuccessful()
            result.reason() == "OK"
    }

    def 'Success with domain events should return them'() {
        given:
            List<DomainEvent> events = List.of(DomainEventFixture.randomEvent())
            Result success = Result.success(events)
        expect:
            success.isSuccessful()
            success.events() == events
    }

    def 'Failure should not return any events'() {
        given:
            Result success = Result.failure("Of any reason")
        expect:
            success.isFailure()
            success.events().isEmpty()
    }
}
