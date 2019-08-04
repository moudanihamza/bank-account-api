package com.kata.transaction;

import com.kata.account.AccountID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryJpaRepository extends JpaRepository<TransactionHistory, TransactionHistoryId> {
   List<TransactionHistory> findByPayerOrPayee(AccountID payer, AccountID Payee);
}
