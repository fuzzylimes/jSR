package com.fuzzylimes.jsr.util;

/**
 * Custom Exception to specify when an error has occurred during a query to the REST APIs.
 */
public class UnexpectedResponseException extends Exception {

    /**
     * Creates an UnexpectedResponseException when an unexpected response is returned when performing a query to
     * SpeedRun.com. This can happen for any number of things, like a 404 for a non-existent resource, or an invalid
     * payload.
     *
     * @param errorMessage Message to be included in exception
     */
    public UnexpectedResponseException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an UnexpectedResponseException when an unexpected response is returned when performing a query to
     * SpeedRun.com. This can happen for any number of things, like a 404 for a non-existent resource, or an invalid
     * payload.
     *
     * @param errorMessage Message to be included in exception
     * @param err Pass the original error object
     */
    public UnexpectedResponseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
