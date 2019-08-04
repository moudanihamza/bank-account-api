package com.kata.transaction;

import com.kata.account.AccountID;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TransactionHistoryManagementServiceTest {

    @Mock
    private TransactionHistories histories;

    @Autowired
    @InjectMocks
    private TransactionHistoryManagementService sut;

    @Test
    public void findByAccountId_userShouldBeAbleToFindHisTransactions() {
        TransactionHistory transaction1, transaction2;
        AccountID accountId1 = new AccountID("1");
        AccountID accoundId2 = new AccountID("2");
        List<TransactionHistory> result;
        Given:
        transaction1 = new TransactionHistory(
                accountId1, accoundId2,
                new BigDecimal("20.00"), TransactionStatus.COMPLETED
        );
        transaction2 = new TransactionHistory(
                accoundId2, accountId1,
                new BigDecimal("10.00"), TransactionStatus.COMPLETED
        );
        When:
        Mockito.doReturn(Arrays.asList(transaction1, transaction2)).when(histories).findByAccountId(accountId1);
        result = sut.findByAccountId(accountId1);
        Then:
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}