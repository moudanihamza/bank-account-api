package com.talan.kata.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistoryAdapter {

    public static List<TransactionHistoryPresentation> adapt(List<TransactionHistory> histories) {
        return histories
                .stream()
                .map(TransactionHistoryPresentation::new)
                .collect(Collectors.toList());
    }

    public static class TransactionHistoryPresentation {
        public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        @JsonProperty
        String id;
        @JsonProperty
        String payer;
        @JsonProperty
        String payee;
        @JsonProperty
        String creationDate;
        @JsonProperty
        BigDecimal amount;
        @JsonProperty
        String status;

        public TransactionHistoryPresentation(TransactionHistory history) {
            this.id = history.getId().getId();
            this.payer = history.getPayer().getAccountId();
            this.payee = history.getPayee().getAccountId();
            this.creationDate = history.getCreationDate().format(format);
            this.amount = history.getAmount();
            this.status = history.getStatus().name();
        }
    }
}
