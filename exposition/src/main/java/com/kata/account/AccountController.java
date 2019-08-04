package com.kata.account;


import com.kata.exceptions.FunctionalException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@Api(tags = "account management", consumes = "application/json", produces = "application/json")
public class AccountController {

    @Autowired
    private AccountManagementServices accountManagementService;

    @ApiOperation(value = "used to withDraw a specific amount from a specific account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succesfully withDraw amount"),
            @ApiResponse(code = 400, message = "maybe the account not found or the amount is negative and you will get the reason on the body")
    })
    @PatchMapping(value = "/accounts/{accountId}/withDraw",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void withDraw(@RequestBody AccountAdapter.OperationCommand command, @ApiParam(value = "physical id of the account", required = true) @PathVariable("accountId") AccountID accountID) throws FunctionalException {
        accountManagementService.withDraw(accountID, command.amount);
    }


    @ApiOperation(value = "used to deposit a specific amount to a specific account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succesfully deposit amount"),
            @ApiResponse(code = 400, message = "maybe the account not found or the amount is negative and you will get the reason on the body")
    })
    @PatchMapping(value = "/accounts/{accountId}/deposit",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void deposit(@RequestBody AccountAdapter.OperationCommand command, @ApiParam(value = "physical id of the account", required = true) @PathVariable("accountId") AccountID accountID) throws FunctionalException {
        accountManagementService.deposit(accountID, command.amount);
    }


    @ApiOperation(value = "used to transfer a specific amount from a specific account to other")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succesfully deposit amount"),
            @ApiResponse(code = 400, message = "maybe the account not found,the amount is negative or the payer has no sufficient amount and you will get the reason on the body")
    })
    @PatchMapping(value = "/accounts/{payerId}/transfer/{payeeId}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE
    )
    public void transfer(@RequestBody AccountAdapter.OperationCommand command, @ApiParam(value = "physical id of the payer account", required = true) @PathVariable("payerId") AccountID payerId, @ApiParam(value = "physical id of the payee account", required = true) @PathVariable("payeeId") AccountID payeeId) throws FunctionalException {
        accountManagementService.transferAmount(payerId, payeeId, command.amount);
    }
}
