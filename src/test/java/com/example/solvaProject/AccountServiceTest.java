package com.example.solvaProject;

import com.example.solvaProject.model.AccountEntity;
import com.example.solvaProject.model.CurrencyEntity;
import com.example.solvaProject.model.dto.AccountDto;
import com.example.solvaProject.repository.AccountRep;
import com.example.solvaProject.repository.CurrencyRep;
import com.example.solvaProject.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
@SpringBootTest
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private CurrencyRep currencyRep;

    @Mock
    private AccountRep accountRep;


    @Test
    public void testCreateAccountWithCurrencyConversion() {
        // Создаем фиктивные данные для DTO
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
        accountDto.setBalanceUsd(1000.0);
        accountDto.setLimitUsd(0.0);

        // Создаем фиктивные данные для валют
        CurrencyEntity currencyKzt = new CurrencyEntity();
        currencyKzt.setFromCurrency("USD");
        currencyKzt.setToCurrency("KZT");
        currencyKzt.setExchangeRate("420.0");

        CurrencyEntity currencyRub = new CurrencyEntity();
        currencyRub.setFromCurrency("USD");
        currencyRub.setToCurrency("JPY");
        currencyRub.setExchangeRate("110.0");

        // Мокируем поведение currencyRep
        when(currencyRep.findFirstByFromCurrencyAndToCurrency("USD", "KZT")).thenReturn(currencyKzt);
        when(currencyRep.findFirstByFromCurrencyAndToCurrency("USD", "JPY")).thenReturn(currencyRub);

        // Вызываем метод createAccount
        accountService.createAccount(accountDto);

        // Проверяем, что метод save был вызван с правильными данными
        AccountEntity expectedAccountEntity = new AccountEntity();
        expectedAccountEntity.setAccountNumber("123456");
        expectedAccountEntity.setBalanceUsd(1000.0);
        expectedAccountEntity.setLimitUsd(1000.0); // Потому что в limitUsd было 0
        expectedAccountEntity.setBalanceKzt(420000.0); // 1000 USD * 420 KZT/USD
        expectedAccountEntity.setBalanceRub(110000.0); // 1000 USD * 110 JPY/USD

        // Проверяем, что метод save был вызван с ожидаемым объектом AccountEntity
        verify(accountRep, times(1)).save(expectedAccountEntity);
    }
}
