package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionHistoryManagementService  implements TransactionHistoryManagementServices{

    @Autowired
    private TransactionHistories transactionHistories;

    @Override
    public List<TransactionHistory> findByAccountId(AccountID accountID) {
        return transactionHistories.findByAccountId(accountID);
    }
}
