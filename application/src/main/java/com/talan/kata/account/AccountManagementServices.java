package com.talan.kata.account;

import com.talan.kata.account.AccountID;
import com.talan.kata.account.exceptions.AccountNotValidException;
import com.talan.kata.account.exceptions.NotSufficientFundsException;
import com.talan.kata.account.exceptions.NotValidAmountException;
import com.talan.kata.exceptions.FunctionalException;

import java.math.BigDecimal;

public interface AccountManagementServices {
    void withDraw(AccountID accountID, BigDecimal amount) throws AccountNotValidException, NotSufficientFundsException, NotValidAmountException;
    void deposit(AccountID accountID, BigDecimal amount) throws NotValidAmountException, AccountNotValidException;
    public void transferAmount(AccountID payer, AccountID payee, BigDecimal amount) throws FunctionalException;
}
