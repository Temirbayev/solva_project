package com.example.solvaProject.controller;

import com.example.solvaProject.model.dto.AccountDto;
import com.example.solvaProject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity createAccount(@RequestBody AccountDto account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("Account was created");
    }

    @GetMapping("/")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(accountService.accountList());
    }

    @PostMapping("/update/{accountId}")
    public ResponseEntity updateAccount(@PathVariable(name = "accountId") Long id, @RequestBody AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return ResponseEntity.ok("Account updated");
    }

    @GetMapping("/delete/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable(name = "accountId") Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }

    @PostMapping("changeLimit/{accountNumber}")
    @ResponseBody
    public ResponseEntity changeLimit(@PathVariable(name = "accountNumber") String accountNumber, @RequestBody AccountDto accountDto){
        accountService.changeLimit(accountNumber,accountDto);
        return ResponseEntity.ok("limit changed");
    }
}
