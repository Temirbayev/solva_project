package com.example.solvaProject.repository;

import com.example.solvaProject.model.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRep extends JpaRepository<CurrencyEntity, Long> {
    CurrencyEntity findFirstByFromCurrencyAndToCurrency(String from,String to);
}
