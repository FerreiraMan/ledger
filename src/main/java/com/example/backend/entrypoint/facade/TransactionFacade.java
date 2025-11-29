package com.example.backend.entrypoint.facade;

import com.example.backend.entrypoint.request.CreateTransactionRequestDTO;
import com.example.backend.entrypoint.response.CreateTransactionResponseDTO;
import com.example.backend.entrypoint.response.FindTransactionHistoryResponseDTO;

public interface TransactionFacade {

    CreateTransactionResponseDTO createTransaction(Long accountId, CreateTransactionRequestDTO requestDTO);
    FindTransactionHistoryResponseDTO findTransactionHistory(Long accountId);

}
