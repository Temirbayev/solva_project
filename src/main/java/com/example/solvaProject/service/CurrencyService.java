package com.example.solvaProject.service;

import com.example.solvaProject.repository.CurrencyRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRep currencyRep;
}
