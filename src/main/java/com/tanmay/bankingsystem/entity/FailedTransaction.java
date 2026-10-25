
package com.tanmay.bankingsystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class FailedTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;

    private Long fromAccountId;

    private Long toAccountId;

    private BigDecimal amount;

    private String failureReason;

    private LocalDateTime failedAt;

    public FailedTransaction() {
    }

    public FailedTransaction(
            String transactionType,
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount,
            String failureReason) {

        this.transactionType = transactionType;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.failureReason = failureReason;
        this.failedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }
}
