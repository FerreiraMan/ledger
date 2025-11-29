package com.example.backend.core.application.transaction.impl;

import com.example.backend.core.application.transaction.TransactionService;
import com.example.backend.core.application.transaction.request.CreateTransactionRequestModel;
import com.example.backend.core.application.transaction.response.CreateTransactionResponseModel;
import com.example.backend.core.application.transaction.response.FindTransactionHistoryResponseModel;
import com.example.backend.core.domain.transaction.Transaction;
import com.example.backend.core.domain.transaction.TransactionType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    final List<Transaction> transactionList = new ArrayList<>();

    @Override
    public CreateTransactionResponseModel createTransaction(final CreateTransactionRequestModel requestModel) {

        if (BigDecimal.ZERO.compareTo(requestModel.getAmount()) >= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        final BigDecimal currentBalance = computeCurrentBalance(transactionList, requestModel.getAccountId());

        final Transaction transaction =
            switch (requestModel.getTransactionType()) {
                case DEPOSIT -> handleDeposit(requestModel);
                case WITHDRAWAL -> handleWithdrawal(requestModel, currentBalance);
            };

        transactionList.add(transaction);

        final BigDecimal resultingBalance = TransactionType.DEPOSIT.equals(requestModel.getTransactionType()) ?
            currentBalance.add(requestModel.getAmount()) : currentBalance.subtract(requestModel.getAmount());

        return CreateTransactionResponseModel.builder()
            .transactionId(transaction.getId())
            .accountId(transaction.getAccountId())
            .resultingBalance(resultingBalance)
            .build();
    }

    @Override
    public FindTransactionHistoryResponseModel findTransactionHistory(final Long accountId) {

        final List<Transaction> accountTransactionHistory = this.transactionList.stream()
            .filter(transaction -> accountId.equals(transaction.getAccountId())).toList();

        return FindTransactionHistoryResponseModel.builder()
            .accountId(accountId)
            .transactions(accountTransactionHistory)
            .build();
    }

    private BigDecimal computeCurrentBalance(final List<Transaction> transactionList, final Long accountId) {

        return transactionList.stream()
            .filter(transaction -> accountId.equals(transaction.getAccountId()))
            .map(transaction -> {
                if (TransactionType.DEPOSIT.equals(transaction.getTransactionType())) {
                    return transaction.getAmount();
                } else {
                    return transaction.getAmount().negate();
                }
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Transaction handleDeposit(final CreateTransactionRequestModel requestModel) {

        return new Transaction(requestModel.getAccountId(), TransactionType.DEPOSIT, requestModel.getAmount());
    }

    private Transaction handleWithdrawal(final CreateTransactionRequestModel requestModel, final BigDecimal currentBalance) {

        if (requestModel.getAmount().compareTo(currentBalance) > 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        return new Transaction(requestModel.getAccountId(), TransactionType.WITHDRAWAL, requestModel.getAmount());
    }

}
