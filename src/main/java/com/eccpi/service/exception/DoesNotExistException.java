package com.eccpi.service.exception;

public class DoesNotExistException extends RuntimeException {
    public DoesNotExistException(Throwable cause) {
        super(cause);
    }

    public DoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoesNotExistException(String message) {
        super(message);
    }

    public DoesNotExistException() {
    }
}
