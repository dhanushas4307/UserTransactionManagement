package com.user.transactions.service;

import com.user.transactions.entity.Balance;
import com.user.transactions.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepo;

    public BalanceService(BalanceRepository balanceRepository){
        this.balanceRepo = balanceRepository;
    }

    public Balance getBalanceByAccountId(Long accountId, String currency){
        return balanceRepo.findByAccountIdAndCurrency(accountId, currency);
    }

    public void updateNewBalance(Long accountId, BigDecimal currentBalance) {
        Balance balance = balanceRepo.findById(accountId).get();
        balance.setAmount(currentBalance);
//        DateTime currentDateAndTime = new DateTime(System.currentTimeMillis(), DateTimeZone.UTC);
        balance.setUpdatedOn(Date.valueOf(LocalDate.now()));
        balanceRepo.save(balance);
    }
}

