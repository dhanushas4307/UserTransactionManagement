package com.user.transactions.dto;


import com.user.transactions.entity.TransactionType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionAmountDto {
    private Long accountId;
    private String name;
    private BigDecimal amount;
    private String currency;
    private TransactionType transactionType;
    private String description;

    public TransactionAmountDto(Long accountId, String name, BigDecimal amount, String currency,
                                TransactionType transactionType, String description)
    {
        this.accountId = accountId;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.transactionType = transactionType;
        this.description = description;
    }

}
