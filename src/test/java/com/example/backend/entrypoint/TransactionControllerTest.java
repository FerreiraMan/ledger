package com.example.backend.entrypoint;

import static com.example.backend.entrypoint.TransactionController.ROOT_PATH;
import static com.example.backend.entrypoint.TransactionController.TRANSACTION_HISTORY;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.backend.core.domain.transaction.TransactionType;
import com.example.backend.entrypoint.facade.TransactionFacade;
import com.example.backend.entrypoint.request.CreateTransactionRequestDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private TransactionFacade transactionFacade;

    @Test
    public void createTransaction_withInvalidAccountId_returnBadRequest() throws Exception {
        // GIVEN
        final String accountId = "invalid";
        final CreateTransactionRequestDTO validDTO = CreateTransactionRequestDTO.builder()
            .amount(BigDecimal.TEN)
            .transactionType(TransactionType.DEPOSIT)
            .build();

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH + "/" + accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(validDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_withoutSpecifyingAccountId_returnNotFound() throws Exception {
        // GIVEN
        final CreateTransactionRequestDTO validDTO = CreateTransactionRequestDTO.builder()
            .amount(BigDecimal.TEN)
            .transactionType(TransactionType.DEPOSIT)
            .build();

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(validDTO)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void findTransactionHistory_withInvalidAccountId_returnBadRequest() throws Exception {
        // GIVEN
        final String accountId = "invalid";

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .get(ROOT_PATH + "/" + accountId + TRANSACTION_HISTORY))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_withoutSpecifyingAmount_returnBadRequest() throws Exception {
        // GIVEN
        final Long accountId = 1L;
        final CreateTransactionRequestDTO validDTO = CreateTransactionRequestDTO.builder()
            .transactionType(TransactionType.DEPOSIT)
            .build();

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH + "/" + accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(validDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_withoutSpecifyingTransactionType_returnBadRequest() throws Exception {
        // GIVEN
        final Long accountId = 1L;
        final CreateTransactionRequestDTO validDTO = CreateTransactionRequestDTO.builder()
            .amount(BigDecimal.TEN)
            .build();

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH + "/" + accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(validDTO)))
            .andExpect(status().isBadRequest());
    }

}
