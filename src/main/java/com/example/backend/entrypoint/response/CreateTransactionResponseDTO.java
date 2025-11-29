package com.example.backend.entrypoint.response;

import com.example.backend.core.application.transaction.response.CreateTransactionResponseModel;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionResponseDTO {

    private UUID transactionId;
    private Long accountId;
    private BigDecimal resultingBalance;

    public static CreateTransactionResponseDTO from(CreateTransactionResponseModel model) {
        return CreateTransactionResponseDTO.builder()
            .transactionId(model.getTransactionId())
            .accountId(model.getAccountId())
            .resultingBalance(model.getResultingBalance())
            .build();
    }
}
