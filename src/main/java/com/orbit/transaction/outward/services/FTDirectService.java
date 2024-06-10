package com.orbit.transaction.outward.services;

import com.orbit.transaction.inward.dto.request.FundTransferRequest;
import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.model.NIPRequestType;

public interface FTDirectService {
    boolean savedFundTransfer(FundTransferResponse response, NIPRequestType inward);
    FundTransferResponse performFundTransferDirectCredit(FundTransferRequest request);
}
