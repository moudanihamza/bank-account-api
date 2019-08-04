package com.kata.account.exceptions;


import com.kata.exceptions.FunctionalException;

public class AccountNotValidException extends FunctionalException {
    public AccountNotValidException() {
        super("Account Not Valid", "account.notValid");
    }
}
