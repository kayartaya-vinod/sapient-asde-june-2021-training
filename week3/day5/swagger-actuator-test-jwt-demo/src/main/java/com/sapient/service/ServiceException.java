package com.sapient.service;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String arg0) {
        super(arg0);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
