package com.kata.account;

import com.kata.account.exceptions.AccountNotValidException;
import com.kata.account.exceptions.NotSufficientFundsException;
import com.kata.account.exceptions.NotValidAmountException;
import com.kata.exceptions.FunctionalException;
import com.kata.transaction.TransactionHistories;
import com.kata.transaction.TransactionHistory;
import com.kata.transaction.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountManagementService implements AccountManagementServices {

    @Autowired
    private Accounts accounts;

    @Autowired
    private TransactionHistories transactionHistories;

    @Override
    public void withDraw(AccountID accountID, BigDecimal amount) throws AccountNotValidException, NotSufficientFundsException, NotValidAmountException {
        Account account = accounts.findById(accountID).orElseThrow(AccountNotValidException::new);
        account.withdrawAmount(amount);
    }

    @Override
    public void deposit(AccountID accountID, BigDecimal amount) throws NotValidAmountException, AccountNotValidException {
        Account account = accounts.findById(accountID).orElseThrow(AccountNotValidException::new);
        account.deposit(amount);
    }

    @Override
    public void transferAmount(AccountID payer, AccountID payee, BigDecimal amount) throws FunctionalException {
        Account accounts1 = accounts.findById(payer).orElseThrow(AccountNotValidException::new);
        Account accounts2 = accounts.findById(payee).orElseThrow(AccountNotValidException::new);
        accounts1.withdrawAmount(amount);
        accounts2.deposit(amount);
        transactionHistories.save(new TransactionHistory(payer, payee, amount, TransactionStatus.COMPLETED));
    }


}
