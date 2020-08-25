package com.artsgard.socioregister.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UniqueConstraintException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UniqueConstraintException() {
    }

    public UniqueConstraintException(String message) {
        super(message);
    }

    public UniqueConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
}
