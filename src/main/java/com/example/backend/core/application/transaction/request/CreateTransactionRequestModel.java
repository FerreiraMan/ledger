package com.example.backend.core.application.transaction.request;

import com.example.backend.core.domain.transaction.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionRequestModel {

    private Long accountId;
    private TransactionType transactionType;
    private String amount;

}
