package main.java.com.sapient.asde.batch5.authservice;

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
