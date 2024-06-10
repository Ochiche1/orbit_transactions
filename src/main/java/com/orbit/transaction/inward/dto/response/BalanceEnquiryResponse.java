package com.orbit.transaction.inward.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceEnquiryResponse {
    private String sessionId;
    private String destinationInstitutionCode;
    private int channelCode;
    private String authorizationCode;
    private String targetAccountName;
    private String targetAccountNumber;
    private String targetBankVerificationNumber;
    private BigDecimal availableBalance;
    private String responseCode;
}
