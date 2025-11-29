package com.example.backend.entrypoint.facade;

import com.example.backend.entrypoint.response.FindCurrentBalanceResponseDTO;

public interface AccountFacade {

    FindCurrentBalanceResponseDTO findCurrentBalance(Long accountId);

}
