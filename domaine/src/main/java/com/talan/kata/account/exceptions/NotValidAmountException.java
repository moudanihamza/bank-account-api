package com.talan.kata.account.exceptions;

import com.talan.kata.exceptions.FunctionalException;

public class NotValidAmountException extends FunctionalException {
    public NotValidAmountException() {
        super("Amount Not Valid", "not.valid.amount");
    }
}
