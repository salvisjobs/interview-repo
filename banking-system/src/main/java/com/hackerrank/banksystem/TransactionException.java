package com.hackerrank.banksystem;

public class TransactionException extends Exception {
    private final transient ResponseError responseError;
    public TransactionException(ResponseError responseError) {
        super(responseError.getMessage());
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
