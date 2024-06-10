package com.orbit.transaction.inward.repository.Impl;

import com.orbit.transaction.inward.model.mapper.AccountType;
import com.orbit.transaction.inward.model.mapper.AccountTypeMapper;
import com.orbit.transaction.inward.model.mapper.BalanceEnquiry;
import com.orbit.transaction.inward.model.mapper.BalanceEnquiryMapper;
import com.orbit.transaction.inward.repository.BalanceEnquiryOps;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class BalanceEnquiryOpsImpl implements BalanceEnquiryOps {

    private final JdbcTemplate jdbcTemplate;
    public static final String BALANCE_ENQUIRY = "SELECT acct_type, cur_bal, col_bal, hold_bal, memo_dr, memo_cr,od_limit, Float_bal_1, Float_bal_2 memo_float from dp_display WHERE acct_no = ? AND acct_type = ?";
    public static final String SELECT_ACCOUNT_TYPE = "SELECT acct_type FROM dp_acct WHERE acct_no = ?";
    @Override
    public List<BalanceEnquiry> queryAccountBalance(String accountNumber) {
        List<AccountType> accountTypeList = queryAccountType(accountNumber);
        if (accountTypeList.isEmpty()) {
            throw new IllegalArgumentException("Account type not found for account number: " + accountNumber);
        }
        String accountType = accountTypeList.get(0).getAccountType();
        return jdbcTemplate.query(BALANCE_ENQUIRY, new BalanceEnquiryMapper(), accountNumber, accountType);
    }





    private List<AccountType> queryAccountType(final String accountNumber){
        return jdbcTemplate.query(SELECT_ACCOUNT_TYPE, new AccountTypeMapper(), accountNumber);
    }
}
