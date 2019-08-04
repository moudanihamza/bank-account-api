package com.kata.account;


import com.kata.account.exceptions.NotSufficientFundsException;
import com.kata.account.exceptions.NotValidAmountException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;


public class AccountTest {

    @Test
    public void depositAmount_userShouldBeAbleToDepositAValidAmount() throws NotValidAmountException {
        Account sut;
        BigDecimal amountToDeposit = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");

        GIVEN:
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        sut.deposit(amountToDeposit);
        THEN:
        Assertions.assertThat(sut.getAmount()).isEqualByComparingTo(actualAmount.add(amountToDeposit));
    }

    @Test(expected = NotValidAmountException.class)
    public void depositAmount_userShouldNotBeAbleToDepositANegativeAmount() throws NotValidAmountException {
        Account sut;
        BigDecimal amountToDeposit = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");
        GIVEN:
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        sut.deposit(amountToDeposit.negate());
    }

    @Test
    public void withdrawAmount_shouldBeAbleToWithDrawAValidAmount() throws NotSufficientFundsException, NotValidAmountException {
        Account sut;
        BigDecimal amount = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");

        GIVEN:

        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        sut.withdrawAmount(amount);

        THEN:
        Assertions.assertThat(sut.getAmount()).isEqualByComparingTo(actualAmount.subtract(amount));
    }

    @Test(expected = NotValidAmountException.class)
    public void withdrawAmount_shouldNotBeAbleToWithDrawNegativeAmount() throws NotSufficientFundsException, NotValidAmountException {
        Account sut;
        BigDecimal amount = new BigDecimal("10.00");
        BigDecimal actualAmount = new BigDecimal("20.00");

        GIVEN:
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        sut.withdrawAmount(amount.negate());
    }


    @Test(expected = NotSufficientFundsException.class)
    public void withdrawAmount_shouldNotBeAbleToWithDrawAmountBiggerThanHisOwnAmount() throws NotSufficientFundsException, NotValidAmountException {
        Account sut;
        BigDecimal amount = new BigDecimal("60.00");
        BigDecimal actualAmount = new BigDecimal("20.00");

        GIVEN:
        sut = new Account(new AccountID("1"), "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), actualAmount);

        WHEN:
        sut.withdrawAmount(amount);
    }


}