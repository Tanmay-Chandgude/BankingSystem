
package com.tanmay.bankingsystem.service;

import com.tanmay.bankingsystem.entity.BankAccount;
import com.tanmay.bankingsystem.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import com.tanmay.bankingsystem.exception.InvalidAccountException;
import java.math.BigDecimal;
import com.tanmay.bankingsystem.exception.AccountNotFoundException;
import com.tanmay.bankingsystem.exception.AccountAlreadyExistsException;
import com.tanmay.bankingsystem.dto.TransferRequest;
import com.tanmay.bankingsystem.exception.InsufficientBalanceException;
import com.tanmay.bankingsystem.exception.InvalidTransferException;
import org.springframework.transaction.annotation.Transactional;
import com.tanmay.bankingsystem.entity.BankTransaction;
import com.tanmay.bankingsystem.repository.BankTransactionRepository;
import com.tanmay.bankingsystem.dto.WithdrawalRequest;


import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankTransactionRepository bankTransactionRepository;


    public BankAccountService(
            BankAccountRepository bankAccountRepository,
            BankTransactionRepository bankTransactionRepository) {

        this.bankAccountRepository = bankAccountRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }

    public BankAccount createAccount(BankAccount account) {

        if (account.getBalance() == null ||
                account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAccountException(
                    "Opening balance cannot be null or negative"
            );
        }

        if (bankAccountRepository.existsByAccountNumber(
                account.getAccountNumber())) {
            throw new AccountAlreadyExistsException(
                    "Account number already exists: "
                            + account.getAccountNumber()
            );
        }

        return bankAccountRepository.save(account);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }


    public BankAccount getAccountById(Long id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with ID: " + id
                        )
                );
    }

    @Transactional
    public void transferMoney(TransferRequest request) {

        if (request.getFromAccountId().equals(request.getToAccountId())) {
            throw new InvalidTransferException(
                    "Cannot transfer money to the same account");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransferException(
                    "Transfer amount must be greater than zero");
        }

        BankAccount sender = getAccountById(request.getFromAccountId());
        BankAccount receiver = getAccountById(request.getToAccountId());

        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException(
                    "Insufficient balance in sender account");
        }

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));

        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);
        BankTransaction transaction = new BankTransaction(
                sender.getId(),
                receiver.getId(),
                request.getAmount()
        );

        bankTransactionRepository.save(transaction);
    }


    @Transactional
    public void withdrawMoney(WithdrawalRequest request) {

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransferException(
                    "Withdrawal amount must be greater than zero");
        }

        BankAccount account = getAccountById(request.getAccountId());

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException(
                    "Insufficient balance for withdrawal");
        }

        account.setBalance(
                account.getBalance().subtract(request.getAmount()));

        bankAccountRepository.save(account);

        BankTransaction transaction = new BankTransaction(
                account.getId(),
                null,
                request.getAmount().negate()
        );

        bankTransactionRepository.save(transaction);
    }



}
