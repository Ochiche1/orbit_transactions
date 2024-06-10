package com.orbit.transaction.inward.services.Impl;

import com.orbit.transaction.inward.dto.request.FundTransferRequest;
import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.exceptions.ResponseCode;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.inward.repository.FundTransferOpsService;
import com.orbit.transaction.inward.services.TransactionServices;
import com.orbit.transaction.outward.services.FTDirectService;
import com.orbit.transaction.outward.services.TSQOutwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionServices {

    private static final int TIME_DELAY_IN_MILLIS = 10_000;
    private final FTDirectService ftDirectService;
    private final TSQOutwardService tsqOutwardService;
    private final FundTransferOpsService fundTransferOpsService;


    @Override
    public FundTransferResponse executeFundTransfer(FundTransferRequest request) {
        FundTransferResponse response = buildResponse(request);

        boolean isFundTransferSaved = fundTransferOpsService.saveIntoFundTransferDebit(response, NIPRequestType.INWARD);

        if (!isFundTransferSaved) {
            response.setResponseCode(ResponseCode.SYSTEM_MALFUNCTION);
        }

        if (response.getResponseCode().equals(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY)) {

            Executors.newSingleThreadExecutor()
                .submit(() -> {
                    try {
                        Thread.sleep(TIME_DELAY_IN_MILLIS);
                        tsqOutwardService.transactionStatusQuery(response, NIPRequestType.INWARD);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        }

        return response;
    }


    private FundTransferResponse buildResponse(FundTransferRequest request) {

        FundTransferResponse response = validateRequest(request);
        response.setSessionId(response.getSessionId());
        response.setNameEnquiryRef(request.getNameEnquiryRef());
        response.setDestinationInstitutionCode(request.getDestinationInstitutionCode());
        response.setChannelCode(request.getChannelCode());
        response.setBeneficiaryAccountName(request.getBeneficiaryAccountName());
        response.setBeneficiaryAccountNumber(request.getBeneficiaryAccountNumber());
        response.setBeneficiaryBankVerificationNumber(request.getBeneficiaryBankVerificationNumber());
        response.setBeneficiaryKYCLevel(request.getBeneficiaryKYCLevel());
        response.setOriginatorAccountName(request.getOriginatorAccountName());
        response.setOriginatorAccountNumber(request.getOriginatorAccountNumber());
        response.setOriginatorBankVerificationNumber(request.getOriginatorBankVerificationNumber());
        response.setOriginatorKYCLevel(request.getOriginatorKYCLevel());
        response.setTransactionLocation(request.getTransactionLocation());
        response.setPaymentReference(request.getPaymentReference());
        response.setAmount(request.getAmount());

        return response;
    }

    public FundTransferResponse validateRequest(FundTransferRequest request) {

        FundTransferResponse response = new FundTransferResponse();

        if (request.getNarration().length() > 100) {
            String narration = request.getNarration().substring(0, 100);
            response.setNarration((narration)
            ); //remove special characters from narration
        }
        response.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY);



        return response;
    }
}
