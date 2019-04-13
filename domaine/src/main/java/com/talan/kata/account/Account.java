package com.talan.kata.account;


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



}
