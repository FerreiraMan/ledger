package com.example.backend.entrypoint;

import com.example.backend.entrypoint.facade.AccountFacade;
import com.example.backend.entrypoint.response.FindCurrentBalanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AccountController.ROOT_PATH)
@RequiredArgsConstructor
public class AccountController {

    public static final String ROOT_PATH = "/api/accounts";
    public static final String ACCOUNT_ID_PATH = "/{accountId}";
    public static final String BALANCE = "/balance";

    private final AccountFacade accountFacade;

    @GetMapping(path = ACCOUNT_ID_PATH + BALANCE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindCurrentBalanceResponseDTO> findCurrentBalance(@PathVariable final Long accountId) {

        return new ResponseEntity<>(accountFacade.findCurrentBalance(accountId), HttpStatus.OK);
    }
}
