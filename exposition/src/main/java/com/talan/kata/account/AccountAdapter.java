package com.talan.kata.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

class AccountAdapter {

    public static class OperationCommand {
        @JsonProperty
        BigDecimal amount;

        public OperationCommand() {
        }
    }
}
