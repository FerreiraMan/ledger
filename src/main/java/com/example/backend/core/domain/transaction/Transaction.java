package com.example.backend.core.domain.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public final class Transaction {

    private final UUID id;
    private final Long accountId;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final Instant timestamp;

    public Transaction(final Long accountId, final TransactionType transactionType, final BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = Instant.now();
    }

}
