
package com.tanmay.bankingsystem.service;

import com.tanmay.bankingsystem.entity.BankAccount;
import com.tanmay.bankingsystem.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import com.tanmay.bankingsystem.exception.InvalidAccountException;
import java.math.BigDecimal;
import com.tanmay.bankingsystem.exception.AccountNotFoundException;
import com.tanmay.bankingsystem.exception.AccountAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
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

}
