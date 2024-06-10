package com.orbit.transaction.inward.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class MandateAmount {
    private BigDecimal mandateAmount;
    private String mandateReference;
}
