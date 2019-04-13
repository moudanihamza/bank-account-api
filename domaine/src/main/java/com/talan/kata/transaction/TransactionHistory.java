package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION_HISTORY")
public class TransactionHistory {

    @EmbeddedId
    private final TransactionHistoryId id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "accountId", column = @Column(name = "payer_id"))
    })
    private final AccountID payer;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "accountId", column = @Column(name = "payee_id"))
    })
    private final AccountID payee;

    private final LocalDateTime creationDate;
    private final BigDecimal amount;
    private final TransactionStatus status;

    public TransactionHistory() {
        this.id = null;
        this.payer = null;
        this.payee = null;
        this.creationDate = null;
        this.amount = null;
        this.status = null;
    }

    public TransactionHistory(AccountID payer, AccountID payee, BigDecimal amount, TransactionStatus status) {
        this.id = new TransactionHistoryId();
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.status = status;
        this.creationDate = LocalDateTime.now(Clock.systemUTC());
    }

    public TransactionHistory(TransactionHistoryId id, AccountID payer, AccountID payee, LocalDateTime creationDate, BigDecimal amount, TransactionStatus status) {
        this.id = id;
        this.payer = payer;
        this.payee = payee;
        this.creationDate = creationDate;
        this.amount = amount;
        this.status = status;
    }

    public TransactionHistoryId getId() {
        return id;
    }

    public AccountID getPayer() {
        return payer;
    }

    public AccountID getPayee() {
        return payee;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "id=" + id +
                ", payer='" + payer + '\'' +
                ", payee='" + payee + '\'' +
                ", creationDate=" + creationDate +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
