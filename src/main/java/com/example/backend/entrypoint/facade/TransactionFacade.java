package com.example.backend.entrypoint.facade;

import com.example.backend.entrypoint.request.CreateTransactionRequestDTO;
import com.example.backend.entrypoint.response.CreateTransactionResponseDTO;

public interface TransactionFacade {

    CreateTransactionResponseDTO createTransaction(Long accountId, CreateTransactionRequestDTO requestDTO);

}
