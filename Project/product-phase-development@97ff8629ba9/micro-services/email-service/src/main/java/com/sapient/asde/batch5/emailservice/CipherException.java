package com.sapient.asde.batch5.emailservice;

public class CipherException extends RuntimeException {

    public CipherException() {
    }

    public CipherException(String message) {
        super(message);
    }

    public CipherException(Throwable cause) {
        super(cause);
    }

}
