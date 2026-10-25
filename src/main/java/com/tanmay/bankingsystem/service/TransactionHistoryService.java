
package com.tanmay.bankingsystem.service;

import com.tanmay.bankingsystem.entity.BankTransaction;
import com.tanmay.bankingsystem.repository.BankAccountRepository;
import com.tanmay.bankingsystem.repository.BankTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService {

    private final BankTransactionRepository bankTransactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public TransactionHistoryService(
            BankTransactionRepository bankTransactionRepository,
            BankAccountRepository bankAccountRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankTransaction> getAllTransactions() {
        return bankTransactionRepository.findAll();
    }

    public List<BankTransaction> getTransactionsByAccountId(Long accountId) {

        if (!bankAccountRepository.existsById(accountId)) {
            throw new com.tanmay.bankingsystem.exception.AccountNotFoundException(
                    "Account not found with ID: " + accountId);
        }

        return bankTransactionRepository
                .findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}
