package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;
import com.talan.kata.transaction.TransactionHistory;

import java.util.List;

public interface TransactionHistoryManagementServices {

    public List<TransactionHistory> findByAccountId(AccountID accountID);
}
