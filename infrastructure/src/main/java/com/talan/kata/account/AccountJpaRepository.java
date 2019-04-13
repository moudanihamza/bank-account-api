package com.talan.kata.account;

import com.talan.kata.account.Account;
import com.talan.kata.account.AccountID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, AccountID> {
}
