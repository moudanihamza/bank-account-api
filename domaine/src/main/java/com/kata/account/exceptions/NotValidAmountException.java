package com.kata.account.exceptions;

import com.kata.exceptions.FunctionalException;

public class NotValidAmountException extends FunctionalException {
    public NotValidAmountException() {
        super("Amount Not Valid", "not.valid.amount");
    }
}
