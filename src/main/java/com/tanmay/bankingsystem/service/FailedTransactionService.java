
package com.tanmay.bankingsystem.service;

import com.tanmay.bankingsystem.entity.FailedTransaction;
import com.tanmay.bankingsystem.repository.FailedTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class FailedTransactionService {

    private final FailedTransactionRepository failedTransactionRepository;

    public FailedTransactionService(
            FailedTransactionRepository failedTransactionRepository) {
        this.failedTransactionRepository = failedTransactionRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordFailure(
            String transactionType,
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount,
            String failureReason) {

        FailedTransaction failedTransaction = new FailedTransaction(
                transactionType,
                fromAccountId,
                toAccountId,
                amount,
                failureReason
        );

        failedTransactionRepository.save(failedTransaction);
    }
}
