package com.talan.kata.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

class TransactionHistoryAdapter {

    static List<TransactionHistoryPresentation> adapt(List<TransactionHistory> histories) {
        return histories
                .stream()
                .map(TransactionHistoryPresentation::new)
                .collect(Collectors.toList());
    }

    @ApiModel(value = "TransactionHistory",description = "this object is used to present transaction history")
    static class TransactionHistoryPresentation {
        static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        @JsonProperty
        String id;

        @ApiModelProperty(value = "physical id of the account")
        @JsonProperty
        String payer;

        @ApiModelProperty(value = "physical id of the account")
        @JsonProperty
        String payee;

        @ApiModelProperty(value = "creation will be in this format MM/dd/yyyy HH:mm:ss ")
        @JsonProperty
        String creationDate;

        @JsonProperty
        BigDecimal amount;

        @ApiModelProperty(value = "status may be COMPLETED or FAILED")
        @JsonProperty
        String status;

        TransactionHistoryPresentation(TransactionHistory history) {
            this.id = history.getId().getId();
            this.payer = history.getPayer().getAccountId();
            this.payee = history.getPayee().getAccountId();
            this.creationDate = history.getCreationDate().format(format);
            this.amount = history.getAmount();
            this.status = history.getStatus().name();
        }
    }
}
