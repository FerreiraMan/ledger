package com.example.backend.core.application.transaction.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateTransactionResponseModel {

    private UUID transactionId;
    private Long accountId;
    private BigDecimal resultingBalance;
}
