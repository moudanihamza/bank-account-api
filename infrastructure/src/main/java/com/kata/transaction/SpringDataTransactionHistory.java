package com.kata.transaction;

import com.kata.account.AccountID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class SpringDataTransactionHistory implements TransactionHistories {

    @Autowired
    private TransactionHistoryJpaRepository transactionHistoryJpaRepository;

    @Override
    public List<TransactionHistory> findByAccountId(AccountID accountID) {
        return transactionHistoryJpaRepository.findByPayerOrPayee(accountID,accountID);
    }

    @Override
    public void save(TransactionHistory transactionHistory) {
        this.transactionHistoryJpaRepository.save(transactionHistory);
    }

    @Override
    public List<TransactionHistory> findAll() {
        return transactionHistoryJpaRepository.findAll();
    }
}
