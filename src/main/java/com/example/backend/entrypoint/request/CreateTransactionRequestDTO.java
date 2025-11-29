package com.example.backend.entrypoint.request;

import com.example.backend.core.domain.transaction.TransactionType;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionRequestDTO {

    private TransactionType transactionType;
    private BigDecimal amount;
}
