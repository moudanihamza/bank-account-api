package com.talan.kata.transaction;

public enum TransactionStatus {
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }
}
