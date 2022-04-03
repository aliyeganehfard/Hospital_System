package controller.exception;

public class IdException extends RuntimeException{
    public IdException() {
    }

    public IdException(String message) {
        super(message);
    }

    public IdException(String message, Throwable cause) {
        super(message, cause);
    }
}
