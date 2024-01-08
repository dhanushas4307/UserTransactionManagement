package com.user.transactions.service;

import com.user.transactions.entity.Transaction;
import com.user.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountIdIn(Collections.singletonList(accountId));
    }

    public List<Transaction> getTransactionsOfDay(Long accountId, String date) {
        Date requestedDate = Objects.isNull(date) ? Date.valueOf(LocalDate.now()) : Date.valueOf(date);
        return transactionRepository.findAllByAccountIdAndUpdatedOn(accountId, requestedDate);
    }
}
