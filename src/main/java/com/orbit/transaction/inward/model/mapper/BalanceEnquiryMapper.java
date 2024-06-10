package com.orbit.transaction.inward.model.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceEnquiryMapper implements RowMapper<BalanceEnquiry> {
    @Override
    public BalanceEnquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BalanceEnquiry.builder()
                .accountType(rs.getString("acct_type"))
                .accountBalance(rs.getBigDecimal("cur_bal"))
                .colBalance(rs.getString("col_bal"))
                .build();
    }
}
