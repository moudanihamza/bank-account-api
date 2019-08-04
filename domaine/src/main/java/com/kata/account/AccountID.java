package com.kata.account;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountID implements Serializable {

    @Column(name = "ID")
    private final String accountId;

    public AccountID() {
        this.accountId = null;
    }

    public AccountID(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountID accountID = (AccountID) o;
        return Objects.equals(accountId, accountID.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    public String getAccountId() {
        return accountId;
    }
}
