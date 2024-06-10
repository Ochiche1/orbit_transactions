package com.orbit.transaction.outward.repository;

import com.orbit.transaction.inward.dto.response.TransactionStatusQueryResponse;
import com.orbit.transaction.inward.model.ResponseModel;
import com.orbit.transaction.inward.model.TransactionProperties;

public interface TSQOutwardOps {
    ResponseModel postTransactions(TransactionProperties properties);
    boolean saveTSQ(TransactionStatusQueryResponse response);

}
