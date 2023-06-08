package com.example.mapemployee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InavalidDataException extends RuntimeException{
    public InavalidDataException() {
    }

    public InavalidDataException(String message) {
        super(message);
    }

    public InavalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InavalidDataException(Throwable cause) {
        super(cause);
    }

    public InavalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
