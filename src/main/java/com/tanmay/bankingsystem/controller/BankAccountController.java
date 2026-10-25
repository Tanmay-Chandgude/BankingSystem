
package com.tanmay.bankingsystem.controller;

import com.tanmay.bankingsystem.entity.BankAccount;
import com.tanmay.bankingsystem.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.tanmay.bankingsystem.dto.TransferRequest;
import com.tanmay.bankingsystem.dto.WithdrawalRequest;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createAccount(
            @Valid @RequestBody BankAccount account) {

        BankAccount createdAccount =
                bankAccountService.createAccount(account);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAccount);
    }

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById(
            @PathVariable Long id) {

        BankAccount account = bankAccountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }


    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @Valid @RequestBody TransferRequest request) {

        bankAccountService.transferMoney(request);

        return ResponseEntity.ok("Money transferred successfully");
    }


    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(
            @Valid @RequestBody WithdrawalRequest request) {

        bankAccountService.withdrawMoney(request);

        return ResponseEntity.ok("ATM withdrawal successful");
    }

}
