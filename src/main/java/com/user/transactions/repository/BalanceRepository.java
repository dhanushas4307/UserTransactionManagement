package com.user.transactions.repository;

import com.user.transactions.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByAccountIdAndCurrency(Long accountId, String currency);

//    Balance findByAccountId(Long accountId);
}
