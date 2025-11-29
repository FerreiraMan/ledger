package com.example.backend.entrypoint.response;

import com.example.backend.core.application.transaction.response.FindTransactionHistoryResponseModel;
import com.example.backend.core.domain.transaction.Transaction;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindTransactionHistoryResponseDTO {

    private Long accountId;
    private List<Transaction> transactions;

    public static FindTransactionHistoryResponseDTO from(FindTransactionHistoryResponseModel model) {
        return FindTransactionHistoryResponseDTO.builder()
            .accountId(model.getAccountId())
            .transactions(model.getTransactions())
            .build();
    }
}
