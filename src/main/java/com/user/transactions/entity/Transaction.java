package com.user.transactions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TRANSACTION")
    @SequenceGenerator(name = "ID_TRANSACTION")
    private Long id;
    private Long accountId;
    private String name;
    private BigDecimal amount;
    private String currency;
    private TransactionType transactionType;
    private String description;
    private Date updatedOn;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="BALANCE_ID")
    private Balance balance;

    public Transaction(Long accountId, String name, BigDecimal amount, String currency, TransactionType transactionType, String description) {
        this.accountId = accountId;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.transactionType = transactionType;
        this.description = description;
    }

}
