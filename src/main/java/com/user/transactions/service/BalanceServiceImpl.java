package com.user.transactions.service;


import com.user.transactions.entity.Balance;
import com.user.transactions.exception.AccountNotFoundException;
import com.user.transactions.exception.InvalidCurrencyTypeException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

@Component
public class BalanceServiceImpl {
    private final BalanceService balanceService;
    private final CurrencyService currencyService;

    public BalanceServiceImpl(BalanceService balanceService, CurrencyService currencyService){
        this.balanceService = balanceService;
        this.currencyService = currencyService;
    }
    public Balance getBalance(Long accountId, String currency) throws AccountNotFoundException {
        Balance bal = balanceService.getBalanceByAccountId(accountId, currency);
        if(Objects.isNull(bal)){
            throw new AccountNotFoundException();
        }
        return bal;
    }
    /**
     * Below method handles the debit transaction details and update the balance
     * @param accountId
     * @param balance
     * @param currency
     * @param amountToDeduct
     *
     * @return
     * @throws InvalidCurrencyTypeException
     * */
    public void deductFromBalance(Long accountId, String currency, BigDecimal amountToDeduct, Balance balance) throws InvalidCurrencyTypeException {
        if(!currencyService.isValidCurrencyType(currency)){
            throw new InvalidCurrencyTypeException();
        }

        if(!balance.getCurrency().equals(currency)){
            amountToDeduct = getAmountAfterConversion(currency, balance.getCurrency(), amountToDeduct);
        }
        BigDecimal currentBalance = balance.getAmount().subtract(amountToDeduct);
        balanceService.updateNewBalance(accountId, currentBalance);
    }
/**
 * Below method is used for the currency conversion of the transaction process
 * @param amount
 * @param accountHolderCurrency
 * @param transactionCurrency
 *
 * @return finalAmount
 * */
    private BigDecimal getAmountAfterConversion(String transactionCurrency, String accountHolderCurrency, BigDecimal amount) {
        BigDecimal finalAmount = amount.divide(CurrencyService.getCurrencyDtoList().get(transactionCurrency), MathContext.DECIMAL32)
                .multiply(CurrencyService.getCurrencyDtoList().get(accountHolderCurrency), MathContext.DECIMAL32);
        return finalAmount;
    }

    /**
     * Below method handles the credit transaction details and update the balance
     * @param accountId
     * @param balance
     * @param currency
     * @param amountToCredit
     *
     * @return
     * @throws InvalidCurrencyTypeException
     * */
    public void creditToBalance(Long accountId, BigDecimal amountToCredit, String currency, Balance balance ) throws InvalidCurrencyTypeException {
        if(!currencyService.isValidCurrencyType(currency)){
            throw new InvalidCurrencyTypeException();
        }

        if(!balance.getCurrency().equals(currency)){
            amountToCredit = getAmountAfterConversion(currency, balance.getCurrency(), amountToCredit);
        }
        BigDecimal currentBalance = balance.getAmount().add(amountToCredit);
       balanceService.updateNewBalance(accountId, currentBalance);
    }
}
