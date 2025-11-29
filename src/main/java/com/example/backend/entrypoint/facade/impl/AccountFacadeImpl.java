package com.example.backend.entrypoint.facade.impl;

import com.example.backend.core.application.account.AccountService;
import com.example.backend.entrypoint.facade.AccountFacade;
import com.example.backend.entrypoint.response.FindCurrentBalanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;

    @Override
    public FindCurrentBalanceResponseDTO findCurrentBalance(final Long accountId) {

        return FindCurrentBalanceResponseDTO.from(accountService.findCurrentBalance(accountId));
    }

}
