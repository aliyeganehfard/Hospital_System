package controller.exception;

public class PrescriptionNotFoundException extends RuntimeException{
    public PrescriptionNotFoundException() {
    }

    public PrescriptionNotFoundException(String message) {
        super(message);
    }

    public PrescriptionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
