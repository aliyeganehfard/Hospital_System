package controller.exception;

public class TurnBookedException extends RuntimeException{
    public TurnBookedException() {
    }

    public TurnBookedException(String message) {
        super(message);
    }

    public TurnBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}
