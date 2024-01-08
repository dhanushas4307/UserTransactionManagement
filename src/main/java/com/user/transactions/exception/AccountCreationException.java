package com.user.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Exception while creating Account")
 public class AccountCreationException extends RuntimeException {
    public AccountCreationException() {
    }

    public AccountCreationException(String message) {
        super(message);
    }
}