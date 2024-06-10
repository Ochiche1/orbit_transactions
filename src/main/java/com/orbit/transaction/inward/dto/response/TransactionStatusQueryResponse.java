package com.orbit.transaction.inward.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusQueryResponse {
    private String sessionId;
    private int channelCode;
    private String sourceInstitutionCode;
    private String responseCode;

    public TransactionStatusQueryResponse(String sessionId, int channelCode) {
    }
}
