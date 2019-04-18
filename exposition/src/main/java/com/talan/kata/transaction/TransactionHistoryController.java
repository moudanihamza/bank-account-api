package com.talan.kata.transaction;

import com.talan.kata.account.AccountID;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@RestController
@Api(tags = "transaction history", produces = "application/json")
public class TransactionHistoryController {
    @Autowired
    private TransactionHistoryManagementServices transactionHistory;


    @ApiOperation(value = "used to query a bank account's transaction history for any bank transfers to or from a specific account", response = TransactionHistoryAdapter.TransactionHistoryPresentation.class)
    @ApiResponses(value = @ApiResponse(code = 400, message = "maybe the account not found and you will get the reason on the body"))
    @GetMapping(value = "/transactionHistories/{accountID}",
            produces = APPLICATION_JSON_UTF8_VALUE)
    public List<TransactionHistoryAdapter.TransactionHistoryPresentation> findByAccountId(@ApiParam(value = "physical id of the account", required = true) @PathVariable String accountID) {
        return TransactionHistoryAdapter.adapt(transactionHistory.findByAccountId(new AccountID(accountID)));
    }
}
