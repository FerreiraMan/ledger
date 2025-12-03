package com.example.backend.core.application.account.impl;

import com.example.backend.core.application.account.AccountService;
import com.example.backend.core.application.account.response.FindCurrentBalanceResponseModel;
import com.example.backend.core.application.transaction.TransactionService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final TransactionService transactionService;

    // When adding persistence layer, additional methods are needed to assert account existence and to upsert accounts.
    // For now, we assume the account exists
    @Override
    public FindCurrentBalanceResponseModel findCurrentBalance(final Long accountId) {

        final BigDecimal currentBalance = transactionService.computeCurrentBalance(accountId);

        return FindCurrentBalanceResponseModel.builder()
            .accountId(accountId)
            .currentBalance(currentBalance)
            .build();
    }

}
