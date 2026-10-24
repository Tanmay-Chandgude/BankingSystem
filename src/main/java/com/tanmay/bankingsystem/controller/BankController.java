
package com.tanmay.bankingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("/api/bank")
    public String getBankMessage() {
        return "Banking System API is running!";
    }
}
