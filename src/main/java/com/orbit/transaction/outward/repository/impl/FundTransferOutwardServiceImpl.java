package com.orbit.transaction.outward.repository.impl;

import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.outward.repository.FTServices;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FundTransferOutwardServiceImpl implements FTServices {
private final JdbcTemplate template;

//    public boolean hasSavedFundTransfer(FundTransferResponse response, NIPRequestType requestType) {
//        String serviceType = requestType == NIPRequestType.INWARD ? "INWARD" : "OUTWARD";
//        String sql = "insert into nip_xfer_direct_credit(session_id, name_enquiry_ref, destination_institution_code," +
//            " channel_code, ben_account_name, ben_account_number, ben_bvn, ben_kyclevel, ori_account_name, " +
//            " ori_account_number, ori_bvn, ori_kyclevel, transaction_location, narration, payment_reference," +
//            " amount, response_code, service_type) \r\n" +
//            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        int result = template.update(sql, response.Objects.toString(fdr.getSessionId(),"1", response.getNameEnquiryRef(),
//            response.getDestinationInstitutionCode(),
//            response.getChannelCode(),
//            response.getBeneficiaryAccountName(),
//            response.getBeneficiaryAccountNumber(),
//            response.getBeneficiaryBankVerificationNumber(),
//            response.getBeneficiaryKYCLevel() ,
//
//            response.getOriginatorAccountName(),
//            response.getOriginatorAccountNumber(),
//            response.getOriginatorBankVerificationNumber(),
//            response.getOriginatorKYCLevel(),
//            response.getTransactionLocation(),
//            response.getNarration(),
//            response.getPaymentReference(),
//            response.getAmount(),
//            response.getResponseCode(),
//            serviceType);
//        return result >= 0;
//    }
//
    public boolean hasSavedFundTransfer(FundTransferResponse fdr, NIPRequestType requestType) {
        if (fdr.getBeneficiaryBankVerificationNumber() != null && !fdr.getBeneficiaryBankVerificationNumber().isEmpty()) {
            fdr.setBeneficiaryBankVerificationNumber("12345678945");
        }

        if (fdr.getOriginatorBankVerificationNumber() != null && !fdr.getOriginatorBankVerificationNumber().isEmpty()) {
            fdr.setOriginatorBankVerificationNumber("12345678945");
        }


        if(fdr.getOriginatorKYCLevel()  == 0) {
            fdr.setOriginatorKYCLevel(1);
        }
        if (fdr.getTransactionLocation() != null && !fdr.getTransactionLocation().isEmpty()) {
            fdr.setTransactionLocation("11111111111");
        }

        if (fdr.getNameEnquiryRef() != null && !fdr.getNameEnquiryRef().isEmpty()) {
            fdr.setNameEnquiryRef(fdr.getSessionId());
        }

        if (fdr.getNarration() != null && !fdr.getNarration().isEmpty()) {
            fdr.setNarration("NIP Transfer " + fdr.getSessionId());
        }


        System.out.println("Save Successful Records For TSQ");

        String serviceType = requestType == NIPRequestType.INWARD ? "INWARD" : "OUTWARD";

        String sql = "insert into nip_xfer_direct_credit(session_id, name_enquiry_ref, destination_institution_code," +
            " channel_code, ben_account_name, ben_account_number, ben_bvn, ben_kyclevel, ori_account_name, " +
            " ori_account_number, ori_bvn, ori_kyclevel, transaction_location, narration, payment_reference," +
            " amount, response_code, service_type) \r\n" +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        int result = template.update(sql, 	Objects.toString(fdr.getSessionId(),"1"),
            Objects.toString(fdr.getNameEnquiryRef(),"1"),
            Objects.toString(fdr.getDestinationInstitutionCode(),"1"),
            fdr.getChannelCode(),
            fdr.getBeneficiaryAccountName(),
            fdr.getBeneficiaryAccountNumber(),
            fdr.getBeneficiaryBankVerificationNumber(),
           fdr.getBeneficiaryKYCLevel() == 0 ? 1 : fdr.getBeneficiaryKYCLevel(),

            fdr.getOriginatorAccountName(),
            fdr.getOriginatorAccountNumber(),
            fdr.getOriginatorBankVerificationNumber(),
           fdr.getOriginatorKYCLevel() == 0 ? 1 : fdr.getBeneficiaryKYCLevel(),
            fdr.getTransactionLocation(),
            fdr.getNarration(),
            fdr.getPaymentReference(),
            fdr.getAmount(),
            fdr.getResponseCode(),
            serviceType);


        return result >= 0;

    }




}
