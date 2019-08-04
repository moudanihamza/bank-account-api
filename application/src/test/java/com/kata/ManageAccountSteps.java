package com.kata;

import com.kata.account.Account;
import com.kata.account.AccountID;
import com.kata.account.AccountManagementServices;
import com.kata.account.Accounts;
import com.kata.transaction.TransactionHistories;
import com.kata.transaction.TransactionHistory;
import com.kata.transaction.TransactionHistoryId;
import com.kata.transaction.TransactionStatus;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.kata.transaction.TransactionHistoryManagementServices;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ContextConfiguration(classes = ApplicationTestConfig.class)
public class ManageAccountSteps {

    @Autowired
    private Accounts accountsRepository;

    @Autowired
    private TransactionHistories transactionHistoriesRepository;

    @Autowired
    private AccountManagementServices sut;

    @Autowired
    private TransactionHistoryManagementServices sut2;

    private List<TransactionHistory> transactionHistories;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");


    private boolean isValidException;


    @Given("^the list of accounts is:$")
    public void theListOfAccountsAsBellow(List<DummyAccount> accounts) throws Throwable {
        accounts.forEach(dummyAccount -> {
            accountsRepository.save(new Account(dummyAccount.id, dummyAccount.accountNumber, dummyAccount.name, LocalDateTime.parse(dummyAccount.creationDate, dtf), dummyAccount.amount));
        });
    }

    @When("^a user request to deposit (.+) to the account id (\\w+)$")
    public void aUserRequestToDepositToTheAccountWithId(BigDecimal amount, AccountID accountId) throws Throwable {
        sut.deposit(accountId, amount);
    }

    @When("a user request to withDraw (.+) to the account id (\\w+)$")
    public void aUserRequestToWithDrawToTheAccountId(BigDecimal amount, AccountID accountId) throws Throwable {
        sut.withDraw(accountId, amount);
    }


    @Then("^the amount of account become as bellow:$")
    public void theAmountOfAccountBecomeAsBellow(List<DummyAccount> accounts) throws Throwable {

        Map<AccountID, Account> accountMap = accountsRepository.findAll()
                .stream().collect(Collectors.toMap(Account::getAccountID, o -> o));

        List<DummyAccount> resutl = accounts.stream()
                .filter(o -> {
                    Optional<Account> account = Optional.ofNullable(accountMap.get(o.id));
                    if (account.isPresent())
                        return account.get().getAmount().compareTo(o.amount) == 0;
                    return false;
                })
                .collect(Collectors.toList());

        Assertions.assertThat(resutl.size()).isEqualTo(accounts.size());

    }

    @When("^(\\w+) with account id (\\w+) requests to transfer (.+) to (\\w+) with account id (\\w+)$")
    public void whenUserWithAccountNumberBBRequestsToTransferToUserWithAccountNumberBB(String payer, AccountID payerAcoountId, BigDecimal amount, String payee, AccountID payeeAccountId) throws Throwable {
        sut.transferAmount(payerAcoountId, payeeAccountId, amount);
    }

    @And("^the history of transaction saved as bellow:$")
    public void theHistoryOfTransactionSavedAsBellow(List<DummyTransactionHistory> transactionHistories) throws Throwable {
        Map<AccountID, TransactionHistory> transactionHistoryMap = transactionHistoriesRepository.findAll()
                .stream().collect(Collectors.toMap(TransactionHistory::getPayer, o -> o));
        List<DummyTransactionHistory> result = transactionHistories.stream()
                .filter(o -> {
                    Optional<TransactionHistory> t = Optional.ofNullable(transactionHistoryMap.get(o.payer));
                    if (t.isPresent())
                        return t.get().getAmount().compareTo(o.amount) == 0 && t.get().getStatus().equals(o.status);
                    return false;
                })
                .collect(Collectors.toList());
        Assertions.assertThat(result.size()).isEqualTo(transactionHistories.size());
    }

    @Given("^the list of histories transactions as bellow:$")
    public void theListOfHistoriesTransactionsAsBellow(List<DummyTransactionHistory> transactionHistories) throws Throwable {
        transactionHistories.forEach(t -> {
            transactionHistoriesRepository.save(new TransactionHistory(t.id, t.payer, t.payee, LocalDateTime.parse(t.creationDate, dtf), t.amount, t.status));
        });
    }

    @When("^when (\\w+) with account ID (\\w+) request to see his transactions histories$")
    public void whenUserWithAccountNumberBBRequestToSeeHisTransactionsHistories(String user, AccountID accountId) throws Throwable {
        transactionHistories = sut2.findByAccountId(accountId);
    }

    @Then("^he sees the histories as bellow:$")
    public void heSeesTheHistoriesAsBellow(List<DummyTransactionHistory> transactionHistories) throws Throwable {
        Map<TransactionHistoryId, TransactionHistory> transactionHistoryMap = this.transactionHistories
                .stream().collect(Collectors.toMap(TransactionHistory::getId, o -> o));
        List<DummyTransactionHistory> result = transactionHistories.stream().filter(o -> {
            Optional<TransactionHistory> t = Optional.ofNullable(transactionHistoryMap.get(o.id));
            if (t.isPresent()) {
                return t.get().getStatus().equals(o.status) && t.get().getAmount().compareTo(o.amount) == 0 &&
                        t.get().getCreationDate().equals(LocalDateTime.parse(o.creationDate, dtf))
                        && t.get().getPayee().equals(o.payee) && t.get().getPayer().equals(o.payer);
            }
            return false;
        }).collect(Collectors.toList());
        Assertions.assertThat(result.size()).isEqualTo(transactionHistories.size());
    }

    @Given("^the list of existing accouts as bellow:$")
    public void theListOfExistingAccoutsAsBellow() throws Throwable {
    }


    @Then("^he can't$")
    public void heCanT() throws Throwable {
      Assertions.assertThat(isValidException).isTrue();
    }

    @When("^(\\w+) with account id (\\w+) tires to transfer (\\d+.\\d+) to (\\w+) with account id (\\w+)$")
    public void whenUserWithAccountNumberBBTiresToTransferToUserWithAccountNumberBB(AccountID payer, String payerAccountNumber, BigDecimal amount, AccountID payee, String payeeAccountNumber) throws Throwable {
        try {
            sut.transferAmount(payer,payee,amount);
        }catch (Exception e){
            isValidException =true;
        }
    }

    @When("^(\\w+) with account id (\\w+) tires to transfer a negative amount (\\d+.\\d+) to user(\\d+) with account id (\\w+)$")
    public void userWithAccountNumberBBTiresToTransferANegativeAmountToUserWithAccountNumberBB(AccountID payer, String payerAccountNumber, BigDecimal amount, AccountID payee, String payeeAccountNumber) throws Throwable {
        try {
            sut.transferAmount(payer,payee,amount);
        }catch (Exception e){
            isValidException =true;
        }
    }


    class DummyAccount {
        AccountID id;
        String accountNumber;
        String name;
        String creationDate;
        BigDecimal amount;
    }

    class DummyTransactionHistory {
        TransactionHistoryId id;
        AccountID payer;
        AccountID payee;
        BigDecimal amount;
        String creationDate;
        TransactionStatus status;
    }
}
