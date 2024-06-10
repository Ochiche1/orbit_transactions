package com.orbit.transaction.inward.services.Impl;


import com.orbit.transaction.inward.dto.response.BalanceEnquiryResponse;
import com.orbit.transaction.inward.model.mapper.BalanceEnquiry;
import com.orbit.transaction.inward.repository.BalanceEnquiryOps;
import com.orbit.transaction.inward.services.BalanceEnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BalanceEnquiryServiceImpl implements BalanceEnquiryService {

  private final BalanceEnquiryOps balanceEnquiryOps;
    @Override
    public BalanceEnquiryResponse getBalance(String accountNumber) {
        BalanceEnquiry enquiry = balanceEnquiryOps
                .queryAccountBalance(accountNumber).get(0);


        BalanceEnquiryResponse response = new BalanceEnquiryResponse();

        response.setAvailableBalance(enquiry.getAccountBalance());
        response.setTargetAccountNumber(accountNumber);

        return response;
    }
}
