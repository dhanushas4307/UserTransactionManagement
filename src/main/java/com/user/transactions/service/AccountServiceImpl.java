package com.user.transactions.service;

import com.user.transactions.dto.AccountDto;
import com.user.transactions.dto.BalanceDTO;
import com.user.transactions.entity.Account;
import com.user.transactions.entity.Balance;
import com.user.transactions.exception.AccountCreationException;
import com.user.transactions.exception.AccountNotFoundException;
import com.user.transactions.exception.InvalidCurrencyTypeException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountServiceImpl {

    private final AccountService accountService;
    private final CurrencyService currencyService;

    public AccountServiceImpl(AccountService accountService, CurrencyService currencyService){
        this.accountService=accountService;
        this.currencyService = currencyService;
    }

    /**
     * get the complete Account details for specified accountId
     * @param accountId
     * @return Account
     * @throws AccountNotFoundException
    * */
    public Account getAccount(Long accountId) throws AccountNotFoundException{
        Account account = accountService.getAccountById(accountId);
        if(Objects.isNull(account)){
            throw new AccountNotFoundException();
        }
        return account;
    }
    /**
     * Create user Account via controller
     * @param accountDto
     * @return Account
     * @throws AccountCreationException
    * */
    public Account createAccount(AccountDto accountDto) throws AccountCreationException{
        Account newAccount =  new Account(
                accountDto.getAccountId(),
                accountDto.getName(),
                accountDto.getBankName(),
                accountDto.getAccountType(),
                accountDto.getCurrencyType()
        );
        setBalanceForNewAccount(newAccount, accountDto);
        accountService.createNewAccount(newAccount);
        return getAccount(accountDto.getAccountId());
    }

    /**
     * Set the default/manual balance details for newly created account
     * @param newAccount
     * @param balanceDto
     *
     * */
    private void setBalanceForNewAccount(Account newAccount, AccountDto balanceDto) {
        BalanceDTO balanceDTO = Objects.isNull(balanceDto.getBalance())
                ? balanceDto.setDefaultBalance() : balanceDto.getBalance();
        if(!currencyService.isValidCurrencyType(balanceDTO.getCurrency())){
            throw new InvalidCurrencyTypeException();
        }
        Balance balance = newAccount.getBalance();
        if(Objects.isNull(balance)) {
            balance = new Balance();
            balance.setAccountId(balanceDTO.getAccountId());
            balance.setHolderName(balanceDto.getName());
            balance.setAmount(balanceDTO.getAmount());
            balance.setCurrency(balanceDTO.getCurrency());
            balance.setUpdatedOn(balanceDTO.getUpdatedOn());
        }
        newAccount.setBalance(balance);
    }
}
