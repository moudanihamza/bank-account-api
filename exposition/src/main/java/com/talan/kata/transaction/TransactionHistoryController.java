package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class TransactionHistoryController {
    @Autowired
    private TransactionHistoryManagementServices transactionHistory;

    @GetMapping(value = "/transactionHistories/{accountID}",
            produces = APPLICATION_JSON_UTF8_VALUE)
    public List<TransactionHistoryAdapter.TransactionHistoryPresentation> findByAccountId(@PathVariable String accountID) {
        return TransactionHistoryAdapter.adapt(transactionHistory.findByAccountId(new AccountID(accountID)));
    }
}
