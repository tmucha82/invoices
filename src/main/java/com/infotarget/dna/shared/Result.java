package com.infotarget.dna.shared;

import io.vavr.control.Either;

import java.util.List;

public class Result {

    public static Result success() {
        return success(List.of());
    }

    public static Result success(DomainEvent event) {
        return success(List.of(event));
    }

    public static Result success(List<DomainEvent> events) {
        return new Result(Either.right(new Success(events)));
    }

    private final Either<Failure, Success> result;

    private Result(Either<Failure, Success> result) {
        this.result = result;
    }

    public static Result failure(String reason) {
        return new Result(Either.left(new Failure(reason)));
    }

    public boolean isSuccessful() {
        return result.isRight();
    }

    public boolean isFailure() {
        return result.isLeft();
    }

    public String reason() {
        if(result.isLeft()) {
            return result.getLeft().getReason();
        }
        return "OK";
    }

    public List<DomainEvent> events() {
        return result
                .map(Success::getEvents)
                .getOrElse(List.of());
    }
}
