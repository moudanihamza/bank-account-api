package com.kata.account;

import com.kata.account.exceptions.AccountNotValidException;
import com.kata.account.exceptions.NotSufficientFundsException;
import com.kata.account.exceptions.NotValidAmountException;
import com.kata.exceptions.FunctionalException;

import java.math.BigDecimal;

public interface AccountManagementServices {
    void withDraw(AccountID accountID, BigDecimal amount) throws AccountNotValidException, NotSufficientFundsException, NotValidAmountException;
    void deposit(AccountID accountID, BigDecimal amount) throws NotValidAmountException, AccountNotValidException;
    public void transferAmount(AccountID payer, AccountID payee, BigDecimal amount) throws FunctionalException;
}
