package com.orbit.transaction.inward.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class TransactionProperties {
    private String toAccountNumber;
    private String fromAccountNumber;
    private BigDecimal transactionAmount;
    private String transactionDescription;
    private String transactionReference;
    private boolean isReversal;
    private String debitCreditFlag;
}
