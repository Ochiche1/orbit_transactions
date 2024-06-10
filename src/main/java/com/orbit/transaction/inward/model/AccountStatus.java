package com.orbit.transaction.inward.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountStatus {
    private String status;
}
