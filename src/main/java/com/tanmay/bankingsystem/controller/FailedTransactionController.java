package com.tanmay.bankingsystem.controller;

import com.tanmay.bankingsystem.entity.FailedTransaction;
import com.tanmay.bankingsystem.repository.FailedTransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/failed-transactions")
public class FailedTransactionController {

    private final FailedTransactionRepository failedTransactionRepository;

    public FailedTransactionController(
            FailedTransactionRepository failedTransactionRepository) {
        this.failedTransactionRepository = failedTransactionRepository;
    }

    @GetMapping
    public List<FailedTransaction> getAllFailedTransactions() {
        return failedTransactionRepository.findAll();
    }
}
