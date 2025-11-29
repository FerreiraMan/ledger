package com.example.backend.entrypoint;

import com.example.backend.entrypoint.facade.TransactionFacade;
import com.example.backend.entrypoint.request.CreateTransactionRequestDTO;
import com.example.backend.entrypoint.response.CreateTransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = TransactionController.ROOT_PATH)
@RequiredArgsConstructor
public class TransactionController {

    public static final String ROOT_PATH = "/api/transactions";
    public static final String ACCOUNT_ID_PATH = "/{accountId}";

    private final TransactionFacade transactionFacade;

    @PostMapping(path = ACCOUNT_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateTransactionResponseDTO> createTransaction(
        @PathVariable final Long accountId, @RequestBody final CreateTransactionRequestDTO createTransactionRequestDTO) {

        return new ResponseEntity<>(transactionFacade.createTransaction(accountId, createTransactionRequestDTO), HttpStatus.CREATED);
    }


}
