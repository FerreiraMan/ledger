package com.example.backend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.backend.core.application.transaction.impl.TransactionServiceImpl;
import com.example.backend.core.application.transaction.request.CreateTransactionRequestModel;
import com.example.backend.core.application.transaction.response.CreateTransactionResponseModel;
import com.example.backend.core.domain.transaction.TransactionType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void createConcurrentTransactions_noTransactionIsLost() throws InterruptedException {
        //GIVEN
        final CreateTransactionRequestModel requestModel = CreateTransactionRequestModel.builder()
            .accountId(1L)
            .transactionType(TransactionType.DEPOSIT)
            .amount(BigDecimal.ONE)
            .build();

        final int numberOfThreads = 20;

        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        final CountDownLatch latch = new CountDownLatch(numberOfThreads);
        final List<BigDecimal> results = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                final CreateTransactionResponseModel result = transactionService.createTransaction(requestModel);
                results.add(result.getResultingBalance());
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();

        //WHEN
        final BigDecimal finalBalance = transactionService.computeCurrentBalance(1L);

        //THEN
        assertEquals(BigDecimal.valueOf(numberOfThreads), finalBalance);
    }

}
