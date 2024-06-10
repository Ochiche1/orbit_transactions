package com.orbit.transaction.outward.services.impl;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.dto.response.TransactionStatusQueryResponse;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.inward.model.ResponseModel;
import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.outward.repository.TSQOutwardOps;
import com.orbit.transaction.outward.services.TSQOutwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TSQOutwardServiceImpl implements TSQOutwardService {
private final TSQOutwardOps tsqOutwardOps;

    @Override
    public String transactionStatusQuery(FundTransferResponse response, NIPRequestType requestType) {
        String senderAccountNumber = "", beneficiaryAccountNumber = "", debitCreditFlag = "";
        TransactionStatusQueryResponse transactionStatusQueryResponse = new TransactionStatusQueryResponse(response.getSessionId(),
            response.getChannelCode());

        if (requestType.equals(NIPRequestType.INWARD)) {
            senderAccountNumber = response.getBeneficiaryAccountNumber();
            beneficiaryAccountNumber = response.getOriginatorAccountNumber();
            debitCreditFlag = "CR";
        }
        if (requestType.equals(NIPRequestType.OUTWARD)) {
            beneficiaryAccountNumber = response.getBeneficiaryAccountNumber();
            senderAccountNumber = response.getOriginatorAccountNumber();
            debitCreditFlag = "DR";
        }
        TransactionProperties properties = TransactionProperties.builder()
            .debitCreditFlag(debitCreditFlag)
            .fromAccountNumber(senderAccountNumber)
            .toAccountNumber(beneficiaryAccountNumber)
            .isReversal(false)
            .transactionAmount(response.getAmount())
            .transactionReference(response.getSessionId())
            .transactionDescription(response.getNarration())
            .build();

        postTransaction(properties);
        tsqOutwardOps.saveTSQ(transactionStatusQueryResponse);

        return String.valueOf(transactionStatusQueryResponse);
    }


    @Override
    public ResponseModel postTransaction(TransactionProperties properties) {
        return tsqOutwardOps.postTransactions(properties);
    }
}
