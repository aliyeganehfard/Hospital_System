package controller.exception;

public class SecretaryNotFoundException extends RuntimeException{
    public SecretaryNotFoundException() {
    }

    public SecretaryNotFoundException(String message) {
        super(message);
    }

    public SecretaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
