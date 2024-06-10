package com.orbit.transaction.inward.services;

import com.orbit.transaction.inward.dto.request.FundTransferRequest;
import com.orbit.transaction.inward.dto.response.FundTransferResponse;

public interface TransactionServices {
    FundTransferResponse executeFundTransfer(FundTransferRequest request);
}
