package com.example.backend.core.domain.account;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class Account {

    private final Long accountId;

    public Account(final Long accountId) {

        this.accountId = accountId;
    }
}
