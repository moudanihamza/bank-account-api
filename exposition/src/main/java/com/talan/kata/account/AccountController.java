package com.talan.kata.account;


import com.talan.kata.exceptions.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class AccountController {

    @Autowired
    private AccountManagementServices accountManagementService;

    @PatchMapping(value = "/accounts/{accountId}/withDraw",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void withDraw(@RequestBody AccountAdapter.OperationCommand command, @PathVariable("accountId") AccountID accountID) throws FunctionalException {
        accountManagementService.withDraw(accountID, command.amount);
    }

    @PatchMapping(value = "/accounts/{accountId}/deposit",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void deposit(@RequestBody AccountAdapter.OperationCommand command, @PathVariable("accountId") AccountID accountID) throws FunctionalException {
        accountManagementService.deposit(accountID, command.amount);
    }


    @PatchMapping(value = "/accounts/{payerId}/transfer/{payeeId}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void transfer(@RequestBody AccountAdapter.OperationCommand command, @PathVariable("payerId") AccountID payerId, @PathVariable("payeeId") AccountID payeeId) throws FunctionalException {
        accountManagementService.transferAmount(payerId, payeeId, command.amount);
    }
}
