package com.user.transactions.service;

import com.user.transactions.dto.TransactionAmountDto;
import com.user.transactions.entity.Account;
import com.user.transactions.entity.Balance;
import com.user.transactions.entity.Transaction;
import com.user.transactions.entity.TransactionType;
import com.user.transactions.exception.AccountNotFoundException;
import com.user.transactions.exception.InsufficientFundException;
import com.user.transactions.exception.InvalidAmountException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Component
public class TransactionServiceImpl {
    private final AccountServiceImpl accountServiceImpl;
    private final BalanceServiceImpl balanceServiceImpl;
    private final TransactionService transactionService;
    final static String DATE_FORMAT = "yyyy-mm-dd";

    public TransactionServiceImpl(AccountServiceImpl accountServiceImpl, BalanceServiceImpl balanceServiceImpl, TransactionService transactionService) {
        this.accountServiceImpl = accountServiceImpl;
        this.balanceServiceImpl = balanceServiceImpl;
        this.transactionService = transactionService;
    }

    /**
     * Creates the transactions details of each transaction happened and return the transaction details
     * @param transactionAmount
     *
     * @return Transaction
     * @throws InvalidAmountException
     * */
    @Transactional
    public Transaction createTransaction(TransactionAmountDto transactionAmount) throws InvalidAmountException {
        Account account = accountServiceImpl.getAccount(transactionAmount.getAccountId());
        if(Objects.isNull(account)){
            throw new AccountNotFoundException();
        }
        if (transactionAmount.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException();
        }
        Balance balance = balanceServiceImpl.getBalance(transactionAmount.getAccountId(), account.getCurrencyType());
        int balanceComp =  balance.getAmount().subtract(transactionAmount.getAmount()).compareTo(BigDecimal.ZERO);
        if(TransactionType.DEBIT.equals(transactionAmount.getTransactionType())){
            if (balanceComp < 0){
                throw new InsufficientFundException();
            } else{
               balanceServiceImpl.deductFromBalance(account.getId(), transactionAmount.getCurrency(), transactionAmount.getAmount(), account.getBalance());
            }
        } else if(TransactionType.CREDIT.equals(transactionAmount.getTransactionType())) {
            balanceServiceImpl.creditToBalance(account.getId(), transactionAmount.getAmount(), transactionAmount.getCurrency(), account.getBalance());
        }
        Transaction transactionResponse = new Transaction(
            transactionAmount.getAccountId(),
            transactionAmount.getName(),
            transactionAmount.getAmount(),
            transactionAmount.getCurrency(),
            transactionAmount.getTransactionType(),
            transactionAmount.getDescription()
        );
        transactionResponse.setBalance(balance);
        transactionResponse.setUpdatedOn(balance.getUpdatedOn());
        transactionService.createTransaction(transactionResponse);
        return transactionResponse;
    }

    /**
     * get All transactions happened for specified accountId
     * @param accountId
     * @return list of transaction details
     * */
    public List<Transaction> getAllTransactions(Long accountId) {
        return transactionService.getTransactions(accountId);
    }
    /**
     * get all transactions happened for specified accountId for specified day
     *
     * @param accountId
     * @param date
     * @return list of transaction details happened for specified day
     */
    public List<Transaction> getAllTransactionsOfDay(Long accountId, String date) {
        if(!Objects.isNull(date) && !isDateValid(date) ){
           throw new IllegalArgumentException();
        }
        return transactionService.getTransactionsOfDay(accountId,date);
    }
    public boolean isDateValid(String date)
    {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
