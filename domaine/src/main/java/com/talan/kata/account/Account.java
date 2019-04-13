package com.talan.kata.account;


import com.talan.kata.account.exceptions.NotSufficientFundsException;
import com.talan.kata.account.exceptions.NotValidAmountException;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @EmbeddedId
    private final AccountID accountID;
    private final String accountNumber;
    private final String name;
    private final LocalDateTime creationDate;
    private BigDecimal amount;

    public Account() {
        this.accountID = null;
        this.name = null;
        this.creationDate = null;
        this.amount = null;
        this.accountNumber = null;
    }

    public Account(AccountID accountID, String accountNumber, String name, LocalDateTime creationDate, BigDecimal amount) {
        this.accountID = accountID;
        this.name = name;
        this.creationDate = creationDate;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public AccountID getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


   public   void deposit(BigDecimal amountToDeposit) throws NotValidAmountException {
        if (isAmountPositive(amountToDeposit)) {
            throw new NotValidAmountException();
        }
        this.amount = this.amount.add(amountToDeposit);
    }

   public void withdrawAmount(final BigDecimal amountToWithDraw) throws NotValidAmountException, NotSufficientFundsException {
        if (isAmountPositive(amountToWithDraw)) {
            throw new NotValidAmountException();
        }

        if (hasSufficientFunds(amountToWithDraw)) {
            this.amount = this.amount.subtract(amountToWithDraw);
        } else {
            throw new NotSufficientFundsException();
        }
    }


    private boolean isAmountPositive(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean hasSufficientFunds(final BigDecimal amount) {
        return this.getAmount().compareTo(amount) >= 0;
    }

}
