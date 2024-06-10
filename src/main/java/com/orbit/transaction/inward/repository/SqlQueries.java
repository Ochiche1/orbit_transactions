package com.orbit.transaction.inward.repository;

public final class SqlQueries {
    public static final String ACCOUNT_STATUS_LOCKED = "select a.status from dp_acct a where a.acct_no = ? and a.status = 'Locked'";
    public static final String SAVE_INTO_NIP_XTER_DIRECT_DEBIT = "insert into nip_xfer_direct_debit(session_id, name_enquiry_ref, destination_institution_code, channel_code, ben_account_name,\r\n" +
        " ben_account_number, ben_bvn, ben_kyclevel, deb_account_name, deb_account_number, deb_bvn, deb_kyclevel, transaction_location,\r\n" +
        " narration, payment_reference, mandate_reference_number, transaction_fee, amount, response_code)\r\n" +
        " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_MANDATE_AMOUNT = "select amount from nip_mandate_advice where mandate_reference_number =  ltrim(rtrim(?)) and response_code =  ltrim(rtrim(?))";
    public static final String SAVE_NIP_TSQ = "INSERT INTO nip_tsq(session_id, source_institution_code, channel_code, response_code) values(?, ?, ?, ?)";
    public static final String SAVE_TRANSACTION_STATUS_QUERY = "INSERT INTO nip_tsq(session_id, source_institution_code, channel_code, response_code) values(?, ?, ?, ?)";

    public static final String SAVE_INTO_NIP_XTER_DIRECT_CREDIT = "insert into nip_xfer_direct_credit(session_id, name_enquiry_ref, destination_institution_code, channel_code, ben_account_name, ben_account_number, ben_bvn, ben_kyclevel, ori_account_name, ori_account_number, ori_bvn, ori_kyclevel, transaction_location, narration, payment_reference, amount, response_code, service_type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
