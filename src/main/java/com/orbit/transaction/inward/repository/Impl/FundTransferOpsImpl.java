package com.orbit.transaction.inward.repository.Impl;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.exceptions.ResponseCode;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.inward.model.mapper.AccountStatusMapper;
import com.orbit.transaction.inward.model.mapper.MandateAmountMapper;
import com.orbit.transaction.inward.repository.FundTransferOpsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.orbit.transaction.inward.repository.SqlQueries.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundTransferOpsImpl implements FundTransferOpsService {

    final private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isAccountBlocked(String debitAccountNumber) {
        String status = jdbcTemplate.query(ACCOUNT_STATUS_LOCKED, new AccountStatusMapper(), debitAccountNumber).get(0).getStatus();
        return status.trim().equals("Locked");
    }

    @Override
    public boolean saveIntoFundTransferDebit(FundTransferResponse response, NIPRequestType requestType) {
        String serviceType = requestType == NIPRequestType.INWARD ? "INWARD" : "OUTWARD";
       return jdbcTemplate.update(SAVE_INTO_NIP_XTER_DIRECT_CREDIT, response.getSessionId(),
            response.getNameEnquiryRef(), response.getDestinationInstitutionCode(),
            response.getChannelCode(), response.getBeneficiaryAccountName(),
            response.getBeneficiaryAccountNumber(), response.getBeneficiaryBankVerificationNumber(),
            response.getBeneficiaryKYCLevel(), response.getOriginatorAccountName(),
            response.getOriginatorAccountNumber(), response.getOriginatorBankVerificationNumber(),
            response.getOriginatorKYCLevel(),response.getTransactionLocation(),
            response.getNarration(), response.getPaymentReference(),
           response.getAmount(), response.getResponseCode(), serviceType) == 0;
    }

    @Override
    public BigDecimal getMandateAmount(String mandateReferenceNumber) {
        String  referenceCode = ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY;
        return jdbcTemplate.query(GET_MANDATE_AMOUNT, new MandateAmountMapper(), mandateReferenceNumber, referenceCode)
            .get(0).getMandateAmount();
    }

    @Override
    public boolean isFundTransferSaved(FundTransferResponse response) {
        return jdbcTemplate.update(SAVE_NIP_TSQ, response.getSessionId(), response.getDestinationInstitutionCode(),
            response.getChannelCode(), response.getResponseCode()) == 0;
    }


}
