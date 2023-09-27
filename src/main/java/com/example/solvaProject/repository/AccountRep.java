package com.example.solvaProject.repository;

import com.example.solvaProject.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRep extends JpaRepository<AccountEntity, Long> {
    AccountEntity findFirstByAccountNumber(String accountNumber);
}
