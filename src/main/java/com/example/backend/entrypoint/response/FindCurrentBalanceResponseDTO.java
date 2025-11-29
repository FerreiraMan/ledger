package com.example.backend.entrypoint.response;

import com.example.backend.core.application.account.response.FindCurrentBalanceResponseModel;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCurrentBalanceResponseDTO {

    private Long accountId;
    private BigDecimal currentBalance;

    public static FindCurrentBalanceResponseDTO from(final FindCurrentBalanceResponseModel findCurrentBalanceResponseModel) {

        return FindCurrentBalanceResponseDTO.builder()
            .accountId(findCurrentBalanceResponseModel.getAccountId())
            .currentBalance(findCurrentBalanceResponseModel.getCurrentBalance())
            .build();
    }
}
