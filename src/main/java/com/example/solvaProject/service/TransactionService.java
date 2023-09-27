package com.example.solvaProject.service;

import com.example.solvaProject.model.AccountEntity;
import com.example.solvaProject.model.CurrencyEntity;
import com.example.solvaProject.model.TransactionEntity;
import com.example.solvaProject.repository.AccountRep;
import com.example.solvaProject.repository.CurrencyRep;
import com.example.solvaProject.repository.TransactionRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRep transactionRep;
    private final AccountRep accountRep;
    private final CurrencyRep currencyRep;
    private final AccountService accountService;

    public void createTransfer(TransactionEntity transaction){
        double sum = transaction.getSum();
        AccountEntity accFrom = accountRep.findFirstByAccountNumber(transaction.getAccountFrom());
        AccountEntity accTo = accountRep.findFirstByAccountNumber(transaction.getAccountTo());
        double checkLimit = accFrom.getLimitUsd();

        if (transaction.getCurrencyShortname().equals("KZT")){

            System.out.println("KZT Sender");

            accFrom.setBalanceKzt(accFrom.getBalanceKzt() - sum);
            accTo.setBalanceKzt(accTo.getBalanceKzt() + sum);

            accFrom.setBalanceUsd(checkCashExchange(accFrom.getBalanceKzt(), "KZT", "USD"));
            accTo.setBalanceUsd(checkCashExchange(accTo.getBalanceKzt(), "KZT", "USD"));

            accFrom.setBalanceRub(checkCashExchange(accFrom.getBalanceKzt(), "KZT", "JPY"));
            accTo.setBalanceRub(checkCashExchange(accTo.getBalanceKzt(), "KZT", "JPY"));

            if (sum > checkLimit) {
                // Сумма перевода превышает лимит
                transaction.setLimitExceeded(true);
            } else {
                // Сумма перевода не превышает лимит
                transaction.setLimitExceeded(false);
            }

        } else if (transaction.getCurrencyShortname().equals("RUB")) {
            System.out.println("RUB sender");

            accFrom.setBalanceRub(accFrom.getBalanceRub() - sum);
            accTo.setBalanceRub(accTo.getBalanceRub() + sum);

            accFrom.setBalanceKzt(checkCashExchange(accFrom.getBalanceUsd(), "USD", "KZT"));
            accTo.setBalanceKzt(checkCashExchange(accTo.getBalanceUsd(), "USD", "KZT"));

            accFrom.setBalanceRub(checkCashExchange(accFrom.getBalanceKzt(), "USD", "RUB"));
            accTo.setBalanceRub(checkCashExchange(accTo.getBalanceKzt(), "USD", "RUB"));

            if (sum > checkLimit) {
                // Сумма перевода превышает лимит
                transaction.setLimitExceeded(true);
            } else {
                // Сумма перевода не превышает лимит
                transaction.setLimitExceeded(false);
            }



        }else{
            System.out.println("USD sender");

            accFrom.setBalanceUsd(accFrom.getBalanceUsd() - sum);
            accTo.setBalanceUsd(accTo.getBalanceUsd() + sum);

            accFrom.setBalanceUsd(checkCashExchange(accFrom.getBalanceRub(), "RUB", "USD"));
            accTo.setBalanceUsd(checkCashExchange(accTo.getBalanceRub(), "RUB", "USD"));

            accFrom.setBalanceKzt(checkCashExchange(accFrom.getBalanceRub(), "RUB", "KZT"));
            accTo.setBalanceKzt(checkCashExchange(accTo.getBalanceRub(), "RUB", "KZT"));

            if (sum > checkLimit) {
                // Сумма перевода превышает лимит
                transaction.setLimitExceeded(true);
            } else {
                // Сумма перевода не превышает лимит
                transaction.setLimitExceeded(false);
            }

        }

        transactionRep.save(transaction);
    }
    public double checkCashExchange(double reBalance, String fromCurrency, String toCurrency) {
        CurrencyEntity currencyEntity = currencyRep.findFirstByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        return accountService.changeCurrency(currencyEntity.getExchangeRate(), reBalance);
    }
    public List<TransactionEntity> getAccountsExceedingLimit(){
        return transactionRep.findByLimitExceeded(true);
    }
}
