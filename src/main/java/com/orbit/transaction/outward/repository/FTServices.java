package com.orbit.transaction.outward.repository;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.model.NIPRequestType;

public interface FTServices {
    boolean hasSavedFundTransfer(FundTransferResponse fdr, NIPRequestType requestType);

}
