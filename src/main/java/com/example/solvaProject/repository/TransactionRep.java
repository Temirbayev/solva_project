package com.example.solvaProject.repository;

import com.example.solvaProject.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRep extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> getAllByAccountFromAndLimitExceeded(String accountNumber,boolean limit);
}
