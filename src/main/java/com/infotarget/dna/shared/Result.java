package com.infotarget.dna.shared;

import io.vavr.control.Either;

public class Result {

    public static Result success() {
        return new Result(Either.right(new Success()));
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
}
