package com.kata.transaction;

import com.kata.account.AccountID;

import java.util.List;

public interface TransactionHistories {

    List<TransactionHistory> findByAccountId(AccountID accountID);

    void save(TransactionHistory transactionHistory);

    List<TransactionHistory> findAll();
}
