package com.user.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Currency Type Mismatch")
public class InvalidCurrencyTypeException extends RuntimeException {
    public InvalidCurrencyTypeException() {
    }

    public InvalidCurrencyTypeException(String message) {
        super(message);
    }
}
