package com.example.backend.core.application.account;

import com.example.backend.core.application.account.response.FindCurrentBalanceResponseModel;

public interface AccountService {

    FindCurrentBalanceResponseModel findCurrentBalance(Long accountId);
}
