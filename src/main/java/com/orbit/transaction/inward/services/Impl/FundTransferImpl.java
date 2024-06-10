//package com.orbit.transaction.inward.services.Impl;
//
//import com.orbit.transaction.inward.dto.request.FundTransferRequest;
//import com.orbit.transaction.inward.dto.response.FundTransferResponse;
//import com.orbit.transaction.inward.exceptions.ResponseConstants;
//import com.orbit.transaction.inward.model.ResponseModel;
//import com.orbit.transaction.inward.model.TransactionProperties;
//import com.orbit.transaction.inward.repository.BalanceEnquiryOps;
//import com.orbit.transaction.inward.repository.FundTransferOpsService;
//import com.orbit.transaction.inward.services.BalanceEnquiryService;
//import com.orbit.transaction.inward.services.FundTransferServices;
//import com.orbit.transaction.outward.repository.TSQOutwardOps;
//import com.orbit.transaction.outward.services.TSQOutwardService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.Objects;
//
//import static com.orbit.transaction.inward.exceptions.ResponseCode.*;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class FundTransferImpl implements FundTransferServices {
//    private final FundTransferOpsService fundTransferOpsService;
//    private final BalanceEnquiryService balanceEnquiryService;
//    private final TSQOutwardOps tsqOutwardOps;
//    private final TSQOutwardService tsqOutwardService;
//    @Override
//    public FundTransferResponse doFundTransfer(FundTransferRequest request) {
//        FundTransferResponse response = buildResponseFrom(request);
//
//        if (!fundTransferOpsService.isFundTransferSaved(response)) {
//            response.setResponseCode(SYSTEM_MALFUNCTION);
//        }
//        if (response.getResponseCode().equals(APPROVED_OR_COMPLETED_SUCCESSFULLY)) {
//            BigDecimal totalDebitAmount = request.getAmount().add(request.getTransactionFee());
//            TransactionProperties properties = TransactionProperties.builder()
//                .isReversal(false)
//                .fromAccountNumber(request.getDebitAccountNumber())
//                .debitCreditFlag("DR")
//                .transactionAmount(totalDebitAmount)
//                .transactionDescription(request.getNarration())
//                .transactionReference(request.getSessionId())
//                .toAccountNumber(request.getBeneficiaryAccountNumber())
//                .build();
//            ResponseModel responseModel = tsqOutwardService.postTransaction(properties);
//            if (!responseModel.getResponseCode().trim().equals(ResponseConstants.SUCCEESS_CODE)){
//                response.setResponseCode(SYSTEM_MALFUNCTION);
//            }
//            System.out.println("ACCOUNT BALANCE IN ACCOUNT " + request.getDebitAccountNumber() + " AFTER TRANSACTION IS " + balanceEnquiryService.getBalance(
//                request.getDebitAccountNumber()).getAvailableBalance());
//            fundTransferOpsService.saveIntoFundTransferDebit(response);
//        }
//        return response;
//    }
//
//
//    private FundTransferResponse buildResponseFrom(FundTransferRequest request) {
//        FundTransferResponse response = new FundTransferResponse();
//        response.setSessionId(request.getSessionId());
//        response.setNameEnquiryRef(request.getNameEnquiryRef());
//        response.setDestinationInstitutionCode(request.getDestinationInstitutionCode());
//        response.setChannelCode(request.getChannelCode());
//
//        response.setBeneficiaryAccountName(request.getBeneficiaryAccountName());
//        response.setBeneficiaryAccountNumber(request.getBeneficiaryAccountNumber());
//        response.setBeneficiaryBankVerificationNumber(request.getBeneficiaryBankVerificationNumber());
//        response.setBeneficiaryKYCLevel(request.getBeneficiaryKYCLevel());
//
//        response.setDebitAccountName(request.getDebitAccountName());
//        response.setDebitAccountNumber(request.getDebitAccountNumber());
//        response.setDebitBankVerificationNumber(request.getDebitBankVerificationNumber());
//        response.setDebitKYCLevel(request.getDebitKYCLevel());
//
//        response.setTransactionLocation(request.getTransactionLocation());
//        response.setTransactionFee(request.getTransactionFee());
//        response.setNarration(request.getNarration());
//        response.setAmount(request.getAmount());
//        response.setMandateReferenceNumber(request.getMandateReferenceNumber());
//        response.setPaymentReference(request.getPaymentReference());
//        response.setResponseCode(validateRequest(request));
//        return response;
//    }
//
//
//    private String validateRequest(FundTransferRequest request) {
//
//        BigDecimal availableBalance = balanceEnquiryService.getBalance(request.getDebitAccountNumber())
//            .getAvailableBalance();
//
//        BigDecimal totalDebitAmount = request.getAmount().add(request.getTransactionFee());
//
//        BigDecimal mandateAmount = fundTransferOpsService.getMandateAmount(request.getMandateReferenceNumber());
//
//        if (fundTransferOpsService.isAccountBlocked(request.getDebitAccountNumber())) {
//            System.out.println("ACCOUNT IS BLOCKED! TRANSACTION NOT PERMITTED");
//            return TRANSACTION_NOT_PERMITTED_TO_SENDER;
//        }
//
//        if (totalDebitAmount.compareTo(availableBalance) > 0) {
//            System.out.println("INSUFFICIENT FUNDS");
//            return NO_SUFFICIENT_FUNDS;
//        }
//
//        if (request.getAmount().compareTo(mandateAmount) > 0) {
//            System.out.println("INVALID AMOUNT");
//            return INVALID_AMOUNT;
//        }
//
//        if (Objects.equals(mandateAmount, BigDecimal.ZERO)) {
//            System.out.println("UNABLE TO LOCATE RECORD");
//            return UNABLE_TO_LOCATE_RECORD;
//        }
//        return APPROVED_OR_COMPLETED_SUCCESSFULLY;
//    }
//}
