package com.example.solvaProject.controller;

import com.example.solvaProject.model.TransactionEntity;
import com.example.solvaProject.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/transfer")
    @ResponseBody
    public ResponseEntity createTransfer(@ModelAttribute TransactionEntity transaction){
        transactionService.createTransfer(transaction);
        return ResponseEntity.ok("Transfer apply");
    }

    @GetMapping("/limit")
    @ResponseBody
    public ResponseEntity   getAllLimitTransactions(@RequestParam(name = "accountNumber") String accountNumber) {
        return ResponseEntity.ok(transactionService.allTransactions(accountNumber));
    }
}
