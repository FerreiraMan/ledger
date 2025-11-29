package com.example.backend.entrypoint.request;

import com.example.backend.core.domain.transaction.TransactionType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequestDTO {

    private TransactionType transactionType;
    private BigDecimal amount;
}
