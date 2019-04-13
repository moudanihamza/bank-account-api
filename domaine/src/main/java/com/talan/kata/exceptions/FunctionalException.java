package com.talan.kata.exceptions;

public abstract class FunctionalException extends Exception {

    private String code;

    public FunctionalException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
