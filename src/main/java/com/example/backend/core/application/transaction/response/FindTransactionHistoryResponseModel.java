package com.example.backend.core.application.transaction.response;

import com.example.backend.core.domain.transaction.Transaction;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindTransactionHistoryResponseModel {

    private Long accountId;
    private List<Transaction> transactions;
}
