package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;
import com.talan.kata.transaction.TransactionHistory;
import com.talan.kata.transaction.TransactionHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryJpaRepository extends JpaRepository<TransactionHistory, TransactionHistoryId> {
   List<TransactionHistory> findByPayerOrPayee(AccountID payer, AccountID Payee);
}
