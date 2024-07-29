package com.graphQL.demo.exceptions;

public class NoDataFoundException extends RuntimeException {

    private final int statusCode;
    public NoDataFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }

}