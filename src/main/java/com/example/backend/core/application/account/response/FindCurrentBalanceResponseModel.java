package com.example.backend.core.application.account.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindCurrentBalanceResponseModel {

    private Long accountId;
    private BigDecimal currentBalance;
}
