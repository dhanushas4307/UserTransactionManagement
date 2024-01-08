package com.user.transactions.controller;

import com.user.transactions.dto.AccountDto;
import com.user.transactions.entity.Account;
import com.user.transactions.exception.AccountCreationException;
import com.user.transactions.exception.AccountNotFoundException;
import com.user.transactions.service.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final AccountServiceImpl accountServiceImpl;

    public AccountsController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody AccountDto accountDto) throws AccountCreationException{
        return accountServiceImpl.createAccount(accountDto);
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable("accountId") Long accountId) throws AccountNotFoundException {
       return accountServiceImpl.getAccount(accountId);
    }
}
