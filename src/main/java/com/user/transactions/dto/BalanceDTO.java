package com.user.transactions.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
public class BalanceDTO {
    @Nullable
    private Long accountId;
    @Nullable
    private String holderName;
    @Nullable
    private BigDecimal amount;
    @Nullable
    private String currency;
    @Nullable
    private Date updatedOn;

}
