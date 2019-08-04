package com.kata.account.exceptions;

import com.kata.exceptions.FunctionalException;

public class NotSufficientFundsException extends FunctionalException {
    public NotSufficientFundsException() {
        super("Not Sufficient Funds", "not.sufficient.funds");
    }
}
