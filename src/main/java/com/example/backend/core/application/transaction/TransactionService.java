package com.example.backend.core.application.transaction;

import com.example.backend.core.application.transaction.request.CreateTransactionRequestModel;
import com.example.backend.core.application.transaction.response.CreateTransactionResponseModel;

public interface TransactionService {

    CreateTransactionResponseModel createTransaction(CreateTransactionRequestModel requestModel);

}
