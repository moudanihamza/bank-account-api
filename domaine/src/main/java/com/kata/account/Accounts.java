package com.kata.account;

import java.util.List;
import java.util.Optional;

public interface Accounts {

   Optional<Account> findById(AccountID accountID);
    void save(Account account);
    List<Account> findAll();
}
