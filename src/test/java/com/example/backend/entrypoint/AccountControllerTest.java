package com.example.backend.entrypoint;

import static com.example.backend.entrypoint.AccountController.BALANCE;
import static com.example.backend.entrypoint.AccountController.ROOT_PATH;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.backend.entrypoint.facade.AccountFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private AccountFacade accountFacade;

    @Test
    public void findAccountBalance_withInvalidAccountId_returnBadRequest() throws Exception {
        // GIVEN
        final String accountId = "invalid";

        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .get(ROOT_PATH + "/" + accountId + BALANCE))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void findAccountBalance_withoutSpecifyingAccountId_returnNotFound() throws Exception {
        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .get(ROOT_PATH + BALANCE))
            .andExpect(status().isNotFound());
    }

}
