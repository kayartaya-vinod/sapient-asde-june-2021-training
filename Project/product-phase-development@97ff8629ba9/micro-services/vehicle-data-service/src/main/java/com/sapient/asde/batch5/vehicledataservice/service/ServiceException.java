package com.sapient.asde.batch5.vehicledataservice.service;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */
public class ServiceException extends Exception{

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
    
}
