package com.orbit.transaction.inward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundTransfer {
    private String sessionId;

    private String nameEnquiryRef;

    private String destinationInstitutionCode;

    private int channelCode;

    private String debitAccountName;

    private String debitAccountNumber;

    private String debitBankVerificationNumber;

    private int debitKYCLevel;

    private String beneficiaryAccountName;

    private String beneficiaryAccountNumber;

    private String beneficiaryBankVerificationNumber;

    private int beneficiaryKYCLevel;

    private String transactionLocation;

    private String narration;

    private String paymentReference;

    private String mandateReferenceNumber;

    private BigDecimal transactionFee;

    private BigDecimal amount;
}
