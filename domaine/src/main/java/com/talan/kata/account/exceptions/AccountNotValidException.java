package com.talan.kata.account.exceptions;


import com.talan.kata.exceptions.FunctionalException;

public class AccountNotValidException extends FunctionalException {
    public AccountNotValidException() {
        super("Account Not Valid", "account.notValid");
    }
}
