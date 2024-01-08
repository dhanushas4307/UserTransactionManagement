package com.user.transactions.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long accountId;
    private String name;
    private String bankName;
    private String accountType;
    private String currencyType;
    @Nullable
    private BalanceDTO balance;

    public BalanceDTO setDefaultBalance() {
        return new BalanceDTO(this.accountId, this.name, BigDecimal.valueOf(0.0), this.currencyType, Date.valueOf(LocalDate.now()));
    }
}
