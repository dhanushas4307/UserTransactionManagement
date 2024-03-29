package com.user.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Insufficient Balance")
public class InsufficientFundException extends RuntimeException {
    public InsufficientFundException() {
    }

    public InsufficientFundException(String message) {
        super(message);
    }
}
