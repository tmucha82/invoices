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
}
