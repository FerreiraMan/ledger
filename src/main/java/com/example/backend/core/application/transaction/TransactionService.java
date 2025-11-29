package com.example.backend.core.application.transaction;

import com.example.backend.core.application.transaction.request.CreateTransactionRequestModel;
import com.example.backend.core.application.transaction.response.CreateTransactionResponseModel;
import com.example.backend.core.application.transaction.response.FindTransactionHistoryResponseModel;
import java.math.BigDecimal;

public interface TransactionService {

    CreateTransactionResponseModel createTransaction(CreateTransactionRequestModel requestModel);
    FindTransactionHistoryResponseModel findTransactionHistory(Long accountId);
    BigDecimal computeCurrentBalance(Long accountId);

}
