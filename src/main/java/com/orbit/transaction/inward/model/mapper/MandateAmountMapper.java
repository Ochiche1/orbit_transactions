package com.orbit.transaction.inward.model.mapper;

import com.orbit.transaction.inward.model.MandateAmount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MandateAmountMapper implements RowMapper<MandateAmount> {
    @Override
    public MandateAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MandateAmount.builder()
            .mandateAmount(rs.getBigDecimal("amount"))
            .build();
    }
}
