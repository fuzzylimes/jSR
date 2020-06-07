package com.fuzzylimes.jsr.util;

public class UnexpectedResponseException extends Exception {

    public UnexpectedResponseException(String errorMessage) {
        super(errorMessage);
    }

    public UnexpectedResponseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
