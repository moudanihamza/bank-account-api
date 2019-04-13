package com.talan.kata.account;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void depositAmount() {
        Account sut;
        BigDecimal amountToDeposit = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");
        // a user should be able to deposit a valid amount
        GIVEN:
        // we had the right account with the specific amount
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        // the user deposit the x amount
        sut.deposit(amountToDeposit);
        THEN:
        // if the amount is valid (positive) the older amount become as bellow
        Assertions.assertThat(sut.getAmount()).isEqualByComparingTo(actualAmount.add(amountToDeposit));
    }

    // now we are going to think about the unhappy path what if
    // the given amount is negative? oops what's going on we will withDraw amount inside of deposit

    @Test(expected = NotValidAmountException.class)
    public void depositAmount_userShouldNotBeAbleToDepositANegativeAmount() {
        Account sut;
        BigDecimal amountToDeposit = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");
        // a user should be able to deposit a valid amount
        GIVEN:
        // we had the right account with the specific amount
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        // the user deposit the negative amount
        sut.deposit(amountToDeposit.negate());

        // then an exception will appear
    }


}