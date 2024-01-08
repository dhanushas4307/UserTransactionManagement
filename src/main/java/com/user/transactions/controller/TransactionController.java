package com.user.transactions.controller;


import com.user.transactions.dto.TransactionAmountDto;
import com.user.transactions.entity.Transaction;
import com.user.transactions.exception.AccountNotFoundException;
import com.user.transactions.service.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @GetMapping("/{accountId}/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransaction(@PathVariable("accountId") Long accountId){
        return transactionServiceImpl.getAllTransactions(accountId);
    }
    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactionOfDay(@PathVariable("accountId") Long accountId,
                                                    @RequestParam(required = false) String date ){
        return transactionServiceImpl.getAllTransactionsOfDay(accountId, date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody TransactionAmountDto transactionAmountDto) throws AccountNotFoundException {
        return transactionServiceImpl.createTransaction(transactionAmountDto);
    }
}
