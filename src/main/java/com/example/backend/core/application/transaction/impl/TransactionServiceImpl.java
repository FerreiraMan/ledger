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

        final BigDecimal amount = new BigDecimal(requestModel.getAmount());
        final Long accountId = requestModel.getAccountId();

        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        synchronized (this.transactionList) {
            final BigDecimal currentBalance = this.computeCurrentBalance(requestModel.getAccountId());

            final Transaction transaction =
                switch (requestModel.getTransactionType()) {
                    case DEPOSIT -> handleDeposit(accountId, amount);
                    case WITHDRAWAL -> handleWithdrawal(accountId, amount, currentBalance);
                };

            transactionList.add(transaction);

            final BigDecimal resultingBalance = TransactionType.DEPOSIT.equals(requestModel.getTransactionType()) ?
                currentBalance.add(amount) : currentBalance.subtract(amount);

            return CreateTransactionResponseModel.builder()
                .transactionId(transaction.getId())
                .accountId(transaction.getAccountId())
                .resultingBalance(resultingBalance)
                .build();
        }
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

    @Override
    public BigDecimal computeCurrentBalance(final Long accountId) {

        return this.transactionList.stream()
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

    private Transaction handleDeposit(final Long accountId, final BigDecimal amount) {

        return new Transaction(accountId, TransactionType.DEPOSIT, amount);
    }

    private Transaction handleWithdrawal(final Long accountId, final BigDecimal amount, final BigDecimal currentBalance) {

        if (amount.compareTo(currentBalance) > 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        return new Transaction(accountId, TransactionType.WITHDRAWAL, amount);
    }

}
