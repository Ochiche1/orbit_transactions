package com.orbit.transaction.inward.repository;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.dto.response.TransactionStatusQueryResponse;
import com.orbit.transaction.inward.model.NIPRequestType;

import java.math.BigDecimal;

public interface FundTransferOpsService {
    boolean isAccountBlocked(String debitAccountNumber);
    boolean saveIntoFundTransferDebit(FundTransferResponse response, NIPRequestType requestType);
    BigDecimal getMandateAmount(String mandateReferenceNumber);
    boolean isFundTransferSaved(FundTransferResponse response);

}
