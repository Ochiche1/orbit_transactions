package com.orbit.transaction.inward.repository;



import com.orbit.transaction.inward.model.mapper.BalanceEnquiry;

import java.util.List;

public interface BalanceEnquiryOps {
    List<BalanceEnquiry> queryAccountBalance(final String accountNumber);

}
