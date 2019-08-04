package com.kata.transaction;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TransactionHistoryId implements Serializable {

    @Column(name = "ID")
    private final String id;

    public TransactionHistoryId() {
        this.id = UUID.randomUUID().toString();
    }

    public TransactionHistoryId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistoryId that = (TransactionHistoryId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }
}
