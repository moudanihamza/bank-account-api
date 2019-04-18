package com.talan.kata.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;

class AccountAdapter {

    @ApiModel(value = "Operation", description = "this object is used to update the amount of specific account")
    public static class OperationCommand {
        @JsonProperty
        @ApiModelProperty(value = "amount must be positive",dataType = "java.lang.String",example = "12.12")
        BigDecimal amount;

        public OperationCommand() {
        }
    }
}
