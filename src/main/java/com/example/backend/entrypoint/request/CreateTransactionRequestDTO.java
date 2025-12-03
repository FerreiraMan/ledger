package com.example.backend.entrypoint.request;

import com.example.backend.core.domain.transaction.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequestDTO {

    @NotNull
    private TransactionType transactionType;
    @NotNull
    private String amount;
}
