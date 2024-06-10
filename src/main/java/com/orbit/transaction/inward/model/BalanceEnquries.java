package com.orbit.transaction.inward.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BalanceEnquries {
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
