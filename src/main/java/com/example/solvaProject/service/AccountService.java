package com.example.solvaProject.service;

import com.example.solvaProject.model.AccountEntity;
import com.example.solvaProject.model.CurrencyEntity;
import com.example.solvaProject.model.dto.AccountDto;
import com.example.solvaProject.repository.AccountRep;
import com.example.solvaProject.repository.CurrencyRep;
import com.example.solvaProject.repository.TransactionRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRep accountRep;
    private final TransactionRep transactionRep;
    private final CurrencyRep currencyRep;
    public void createAccount(AccountDto accountDto){
        double accountDto1 = accountDto.getLimitUsd();

        if (accountDto1 == 0){
            accountDto1 = 1000.0;
        }else {
            accountDto1 = accountDto.getLimitUsd();
        }
        AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber(accountDto.getAccountNumber())
                .balanceUsd(accountDto.getBalanceUsd())
                .limitUsd(accountDto1)
                .build();

        CurrencyEntity currencyEntityKzt = currencyRep.findFirstByFromCurrencyAndToCurrency("USD", "KZT");
        double balanceKzt = changeCurrency(currencyEntityKzt.getExchangeRate(), accountEntity.getBalanceUsd());
        accountEntity.setBalanceKzt(balanceKzt);



        CurrencyEntity currencyEntityRub = currencyRep.findFirstByFromCurrencyAndToCurrency("USD", "JPY");
        double balanceRub = changeCurrency(currencyEntityRub.getExchangeRate(), accountEntity.getBalanceUsd());
        accountEntity.setBalanceRub(balanceRub);

        accountRep.save(accountEntity);
    }

    public double changeCurrency(String currencyBalance, double exchangeRate) {
        double balance = Double.parseDouble(currencyBalance);
        return balance * exchangeRate;
    }

    public List<AccountEntity> accountList() {
        return accountRep.findAll();
    }

    public void updateAccount(Long id, AccountDto accountDto) {
        AccountEntity accountEntity = accountRep.findById(id).get();
        accountEntity.setLimitUsd(accountDto.getLimitUsd());
        accountRep.save(accountEntity);
    }

    public void deleteAccount(Long id){
        accountRep.deleteById(id);
    }

    public void changeLimit(String accountNumber, AccountDto accountDto) {
        AccountEntity accountEntity = accountRep.findFirstByAccountNumber(accountNumber);
        accountEntity.setLimitUsd(accountDto.getLimitUsd());
        accountRep.save(accountEntity);
    }
}
