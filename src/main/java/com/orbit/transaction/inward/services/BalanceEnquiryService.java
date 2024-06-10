package com.orbit.transaction.inward.services;


import com.orbit.transaction.inward.dto.response.BalanceEnquiryResponse;

public interface BalanceEnquiryService {
    BalanceEnquiryResponse getBalance(String accountNumber);
}
