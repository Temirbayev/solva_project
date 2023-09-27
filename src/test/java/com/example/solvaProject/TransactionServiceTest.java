//package com.example.solvaProject;
//import com.example.solvaProject.model.AccountEntity;
//import com.example.solvaProject.model.CurrencyEntity;
//import com.example.solvaProject.model.TransactionEntity;
//import com.example.solvaProject.repository.AccountRep;
//import com.example.solvaProject.repository.CurrencyRep;
//import com.example.solvaProject.repository.TransactionRep;
//import com.example.solvaProject.service.AccountService;
//import com.example.solvaProject.service.TransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class TransactionServiceTest {
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @Mock
//    private TransactionRep transactionRep;
//
//    @Mock
//    private AccountRep accountRep;
//
//    @Mock
//    private CurrencyRep currencyRep;
//
//    @Mock
//    private AccountService accountService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreateTransfer_KZT() {
//        // Создаем фиктивные данные для транзакции
//        TransactionEntity transaction = new TransactionEntity();
//        transaction.setSum(1000.0);
//        transaction.setAccountFrom("123456");
//        transaction.setAccountTo("789101");
//        transaction.setCurrencyShortname("KZT");
//
//        // Создаем фиктивные данные для аккаунтов
//        AccountEntity accFrom = new AccountEntity();
//        accFrom.setAccountNumber("123456");
//        accFrom.setBalanceKzt(5000.0);
//
//        AccountEntity accTo = new AccountEntity();
//        accTo.setAccountNumber("789101");
//        accTo.setBalanceKzt(2000.0);
//
//        // Создаем фиктивные данные для валюты
//        CurrencyEntity currencyKzt = new CurrencyEntity();
//        currencyKzt.setFromCurrency("KZT");
//        currencyKzt.setToCurrency("USD");
//        currencyKzt.setExchangeRate(String.valueOf(0.002));
//
//        when(accountRep.findFirstByAccountNumber("123456")).thenReturn(accFrom);
//        when(accountRep.findFirstByAccountNumber("789101")).thenReturn(accTo);
//        when(currencyRep.findFirstByFromCurrencyAndToCurrency("KZT", "USD")).thenReturn(currencyKzt);
//
//        // Вызываем метод createTransfer
//        transactionService.createTransfer(transaction);
//
//        // Проверяем, что балансы аккаунтов обновлены правильно
//        verify(accFrom, times(1)).setBalanceKzt(4000.0); // 5000 KZT - 1000 KZT
//        verify(accTo, times(1)).setBalanceKzt(3000.0);   // 2000 KZT + 1000 KZT
//
//        // Проверяем, что балансы в других валютах также обновлены правильно
//        verify(accFrom, times(1)).setBalanceUsd(8.0);  // 4000 KZT * 0.002 USD/KZT
//        verify(accTo, times(1)).setBalanceUsd(3.0);    // 3000 KZT * 0.002 USD/KZT
//
//        // Проверяем, что транзакция была сохранена
//        verify(transactionRep, times(1)).save(transaction);
//    }
//
//    // Напишите аналогичные тесты для других случаев (USD и RUB)
//}
//
