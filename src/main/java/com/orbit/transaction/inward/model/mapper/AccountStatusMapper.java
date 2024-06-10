package com.orbit.transaction.inward.model.mapper;

import com.orbit.transaction.inward.model.AccountStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStatusMapper implements RowMapper<AccountStatus> {
    @Override
    public AccountStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AccountStatus.builder()
            .status(rs.getString("status"))
            .build();
    }
}
