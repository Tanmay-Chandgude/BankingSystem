
package com.tanmay.bankingsystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WithdrawalRequest {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "Withdrawal amount is required")
    @DecimalMin(
            value = "0.01",
            message = "Withdrawal amount must be greater than zero"
    )
    private BigDecimal amount;

    public WithdrawalRequest() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
