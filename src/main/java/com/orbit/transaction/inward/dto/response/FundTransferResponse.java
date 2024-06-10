package com.orbit.transaction.inward.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResponse {
    private String sessionId;
    private String destinationInstitutionCode;
    private String nameEnquiryRef;
    private int channelCode;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNumber;
    private String beneficiaryBankVerificationNumber;
    private int beneficiaryKYCLevel;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String originatorBankVerificationNumber;
    private int originatorKYCLevel;
    private String transactionLocation;
    private String narration;
    private String paymentReference;
    private BigDecimal amount;
    private String responseCode;

}
