package com.example.backend.entrypoint.facade.impl;

import com.example.backend.core.application.transaction.TransactionService;
import com.example.backend.core.application.transaction.request.CreateTransactionRequestModel;
import com.example.backend.entrypoint.facade.TransactionFacade;
import com.example.backend.entrypoint.request.CreateTransactionRequestDTO;
import com.example.backend.entrypoint.response.CreateTransactionResponseDTO;
import com.example.backend.entrypoint.response.FindTransactionHistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    @Override
    public CreateTransactionResponseDTO createTransaction(final Long accountId, final CreateTransactionRequestDTO requestDTO) {

        final CreateTransactionRequestModel requestModel = CreateTransactionRequestModel.builder()
            .accountId(accountId)
            .transactionType(requestDTO.getTransactionType())
            .amount(requestDTO.getAmount())
            .build();

        return CreateTransactionResponseDTO.from(transactionService.createTransaction(requestModel));
    }

    @Override
    public FindTransactionHistoryResponseDTO findTransactionHistory(final Long accountId) {

        return FindTransactionHistoryResponseDTO.from(transactionService.findTransactionHistory(accountId));
    }

}
