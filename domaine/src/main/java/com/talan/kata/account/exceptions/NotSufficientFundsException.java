package com.talan.kata.account.exceptions;

import com.talan.kata.exceptions.FunctionalException;

public class NotSufficientFundsException extends FunctionalException {
    public NotSufficientFundsException() {
        super("Not Sufficient Funds", "not.sufficient.funds");
    }
}
