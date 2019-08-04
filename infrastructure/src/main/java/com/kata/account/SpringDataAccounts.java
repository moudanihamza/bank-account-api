package com.kata.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Primary
@Repository
public class SpringDataAccounts implements Accounts {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<Account> findById(AccountID accountID) {
        return accountJpaRepository.findById(accountID);
    }

    @Override
    public void save(Account account) {
        accountJpaRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountJpaRepository.findAll();
    }
}
