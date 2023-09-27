//package com.example.solvaProject;
//import com.example.solvaProject.model.CurrencyEntity;
//import com.example.solvaProject.model.dto.AccountDto;
//import com.example.solvaProject.repository.AccountRep;
//import com.example.solvaProject.repository.CurrencyRep;
//import com.example.solvaProject.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AccountServiceTest {
//
//    @InjectMocks
//    private AccountService accountService;
//
//    @Mock
//    private AccountRep accountRepository;
//
//    @Mock
//    private CurrencyRep currencyRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreateAccount() {
//        // Создаем фиктивные данные для валюты
//        CurrencyEntity currencyKzt = new CurrencyEntity();
//        currencyKzt.setFromCurrency("USD");
//        currencyKzt.setToCurrency("KZT");
//        currencyKzt.setExchangeRate(String.valueOf(420.0));
//
//        CurrencyEntity currencyRub = new CurrencyEntity();
//        currencyRub.setFromCurrency("USD");
//        currencyRub.setToCurrency("JPY");
//        currencyRub.setExchangeRate(String.valueOf(110.0));
//
//        when(currencyRepository.findFirstByFromCurrencyAndToCurrency("USD", "KZT")).thenReturn(currencyKzt);
//        when(currencyRepository.findFirstByFromCurrencyAndToCurrency("USD", "JPY")).thenReturn(currencyRub);
//
//        // Создаем фиктивные данные для DTO
//        AccountDto accountDto = new AccountDto();
//        accountDto.setAccountNumber("123456");
//        accountDto.setBalanceUsd(1000.0);
//        accountDto.setLimitUsd(500.0);
//
//        // Вызываем метод createAccount
//        accountService.createAccount(accountDto);
//
//        // Проверяем, что метод save был вызван с правильными данными
//        verify(accountRepository, times(1)).save(argThat(accountEntity -> {
//            // Проверяем, что данные в AccountEntity правильно установлены
//            return accountEntity.getAccountNumber().equals("123456")
//                    && accountEntity.getBalanceUsd() == 1000.0
//                    && accountEntity.getLimitUsd() == 500.0
//                    && accountEntity.getBalanceKzt() == 420000.0 // 1000 USD * 420 KZT/USD
//                    && accountEntity.getBalanceRub() == 110000.0; // 1000 USD * 110 JPY/USD
//        }));
//    }
//}
