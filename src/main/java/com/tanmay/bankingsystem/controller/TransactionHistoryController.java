
package com.tanmay.bankingsystem.controller;

import com.tanmay.bankingsystem.entity.BankTransaction;
import com.tanmay.bankingsystem.service.TransactionHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(
            TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping
    public List<BankTransaction> getAllTransactions() {
        return transactionHistoryService.getAllTransactions();
    }

    @GetMapping("/account/{accountId}")
    public List<BankTransaction> getTransactionsByAccountId(
            @PathVariable Long accountId) {
        return transactionHistoryService
                .getTransactionsByAccountId(accountId);
    }
}
