package com.ssenturk.trendyollinkconvertor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputNotValidException extends RuntimeException{

    /** Custom Exception */
    public InputNotValidException(String message) {
        super(message);
    }
}
