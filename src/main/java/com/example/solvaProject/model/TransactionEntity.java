package com.example.solvaProject.model;

import com.example.solvaProject.model.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "acc_transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_from")
    private String accountFrom;
    @Column(name = "account_to")
    private String accountTo;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "sum")
    private double sum;
    @Column(name = "expense_category")
    private String expenseCategory;
    @CreationTimestamp
    private LocalDateTime transactionDateTime;
    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

}