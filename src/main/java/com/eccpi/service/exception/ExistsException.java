package com.eccpi.service.exception;

public class ExistsException extends RuntimeException {
    public ExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsException(String message) {
        super(message);
    }

    public ExistsException() {
        super();
    }
}
