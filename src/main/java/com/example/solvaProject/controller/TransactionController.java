package com.example.solvaProject.controller;

import com.example.solvaProject.model.TransactionEntity;
import com.example.solvaProject.service.AccountService;
import com.example.solvaProject.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("/transfer")
    @ResponseBody
    public ResponseEntity createTransfer(@ModelAttribute TransactionEntity transaction){
        transactionService.createTransfer(transaction);
        return ResponseEntity.ok("Transfer apply");
    }

    @GetMapping("/limit")
    @ResponseBody
    public ResponseEntity<List<TransactionEntity>> getAccountsExceedingLimit() {
        return ResponseEntity.ok(transactionService.getAccountsExceedingLimit());
    }

}
