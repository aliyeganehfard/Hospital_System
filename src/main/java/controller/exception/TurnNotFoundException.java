package controller.exception;

public class TurnNotFoundException extends RuntimeException{
    public TurnNotFoundException() {
    }

    public TurnNotFoundException(String message) {
        super(message);
    }

    public TurnNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
