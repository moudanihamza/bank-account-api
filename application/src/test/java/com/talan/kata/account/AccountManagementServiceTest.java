package com.talan.kata.account;

import com.talan.kata.account.exceptions.AccountNotValidException;
import com.talan.kata.account.exceptions.NotSufficientFundsException;
import com.talan.kata.account.exceptions.NotValidAmountException;
import com.talan.kata.transaction.TransactionHistories;
import com.talan.kata.transaction.TransactionHistory;
import com.talan.kata.exceptions.FunctionalException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AccountManagementServiceTest {

    @Mock
    private Accounts accounts;

    @Spy
    private TransactionHistories histories;

    @Autowired
    @InjectMocks
    private AccountManagementService sut;

    @Captor
    private ArgumentCaptor<TransactionHistory> transactionHistoryArgumentCaptor;

    @Test
    public void withDraw_userShouldBeAbleToWithDrawAValidAmount() throws AccountNotValidException, NotValidAmountException, NotSufficientFundsException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Account mockedAccount = new Account(accountID, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount);
        Mockito.doReturn(Optional.of(mockedAccount)).when(accounts).findById(accountID);
        WHEN:
        sut.withDraw(accountID, amount);
        THEN:
        verify(accounts).findById(accountID);
        Assertions.assertThat(mockedAccount.getAmount()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test(expected = NotSufficientFundsException.class)
    public void withDraw_userShouldNotBeAbleToWithDrawNotSufficientAmount() throws AccountNotValidException, NotValidAmountException, NotSufficientFundsException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Account mockedAccount = new Account(accountID, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount);
        Mockito.doReturn(Optional.of(mockedAccount)).when(accounts).findById(accountID);
        WHEN:
        sut.withDraw(accountID, amount.add(amount));
        THEN:
        verify(accounts).findById(accountID);
    }

    @Test(expected = NotValidAmountException.class)
    public void withDraw_userShouldNotBeAbleToWithDrawAnegativeAmount() throws AccountNotValidException, NotValidAmountException, NotSufficientFundsException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Account mockedAccount = new Account(accountID, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount);
        Mockito.doReturn(Optional.of(mockedAccount)).when(accounts).findById(accountID);
        WHEN:
        sut.withDraw(accountID, amount.negate());
        THEN:
        verify(accounts).findById(accountID);
    }

    @Test(expected = AccountNotValidException.class)
    public void withDraw_userShouldNotBeAbleToWithDrawAnAmountFromUnexistingAccount() throws AccountNotValidException, NotValidAmountException, NotSufficientFundsException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Mockito.doReturn(Optional.empty()).when(accounts).findById(accountID);
        WHEN:
        sut.withDraw(accountID, amount.negate());
        THEN:
        verify(accounts).findById(accountID);
    }


    @Test
    public void deposit_userShouldBeAbleToDepositAValidAmount() throws AccountNotValidException, NotValidAmountException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Account mockedAccount = new Account(accountID, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount);
        Mockito.doReturn(Optional.of(mockedAccount)).when(accounts).findById(accountID);
        WHEN:
        sut.deposit(accountID, amount);
        THEN:
        verify(accounts).findById(accountID);
        Assertions.assertThat(mockedAccount.getAmount()).isEqualByComparingTo(amount.add(amount));
    }

    @Test(expected = NotValidAmountException.class)
    public void deposit_userShouldBeAbleToDepositANegativeValidAmount() throws AccountNotValidException, NotValidAmountException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Account mockedAccount = new Account(accountID, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount);
        Mockito.doReturn(Optional.of(mockedAccount)).when(accounts).findById(accountID);
        WHEN:
        sut.deposit(accountID, amount.negate());
        THEN:
        verify(accounts).findById(accountID);
    }

    @Test(expected = AccountNotValidException.class)
    public void deposit_userShouldNotBeAbleToDepositAValidAmountTounexistingAccount() throws AccountNotValidException, NotValidAmountException {
        AccountID accountID;
        BigDecimal amount;

        GIVEN:
        accountID = new AccountID("1");
        amount = new BigDecimal("10.00");
        Mockito.doReturn(Optional.empty()).when(accounts).findById(accountID);
        WHEN:
        sut.deposit(accountID, amount.negate());
        THEN:
        verify(accounts).findById(accountID);
    }

    @Test
    public void transferAmount_userShouldBeAbleTransferAValidAmountFromAccountToOther() throws FunctionalException {
        AccountID payerId, payeeId;
        BigDecimal amount1, amount2;
        Account payer, payee;

        GIVEN:
        payerId = new AccountID("1");
        payeeId = new AccountID("2");
        amount1 = new BigDecimal("40.00");
        amount2 = new BigDecimal("20.00");
        payer = new Account(payeeId, "BB12", "toto", LocalDateTime.now(Clock.systemUTC()), amount1);
        payee = new Account(payeeId, "BB13", "titi", LocalDateTime.now(Clock.systemUTC()), amount2);

        WHEN:
        Mockito.doReturn(Optional.of(payer)).when(accounts).findById(payerId);
        Mockito.doReturn(Optional.of(payee)).when(accounts).findById(payeeId);
        Mockito.doNothing().when(histories).save(any(TransactionHistory.class));
        sut.transferAmount(payerId, payeeId, amount1);
        THEN:
        verify(accounts).findById(payeeId);
        verify(accounts).findById(payerId);
        verify(histories).save(transactionHistoryArgumentCaptor.capture());
        TransactionHistory transactionHistory = transactionHistoryArgumentCaptor.getValue();
        Assertions.assertThat(transactionHistory.getPayee()).isEqualTo(payeeId);
        Assertions.assertThat(transactionHistory.getPayer()).isEqualTo(payerId);
        Assertions.assertThat(payee.getAmount()).isEqualByComparingTo(amount1.add(amount2));
    }
}