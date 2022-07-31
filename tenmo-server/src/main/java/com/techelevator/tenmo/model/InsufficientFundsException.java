package com.techelevator.tenmo.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "insufficient funds")
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Insufficient Funds");
    }
}