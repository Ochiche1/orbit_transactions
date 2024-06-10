package com.orbit.transaction.outward.services;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.inward.model.ResponseModel;
import com.orbit.transaction.inward.model.TransactionProperties;

public interface TSQOutwardService {
    String transactionStatusQuery(FundTransferResponse response, NIPRequestType requestType);
    ResponseModel postTransaction(TransactionProperties properties);

}
