package com.user.transactions.service;

import com.user.transactions.entity.Account;
import com.user.transactions.exception.AccountCreationException;
import com.user.transactions.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    public Account getAccountById(Long accountId){
       return accountRepository.findByAccountId(accountId);
    }

    public void createNewAccount(Account newAccount) throws AccountCreationException {
        try {
            accountRepository.save(newAccount);
        }catch(Exception e){
            throw new AccountCreationException();
        }
    }
}
