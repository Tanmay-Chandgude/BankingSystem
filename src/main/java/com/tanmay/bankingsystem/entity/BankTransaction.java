package com.tanmay.bankingsystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccountId;

    private Long toAccountId;

    private BigDecimal amount;

    private LocalDateTime transactionTime;

    public BankTransaction() {
    }

    public BankTransaction(Long fromAccountId,
                           Long toAccountId,
                           BigDecimal amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
}
