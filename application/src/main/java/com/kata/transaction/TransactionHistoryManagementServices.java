package com.kata.transaction;

import com.kata.account.AccountID;

import java.util.List;

public interface TransactionHistoryManagementServices {

    public List<TransactionHistory> findByAccountId(AccountID accountID);
}
