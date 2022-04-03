package controller.exception;

public class ClinicNotFoundException extends RuntimeException{
    public ClinicNotFoundException() {
    }

    public ClinicNotFoundException(String message) {
        super(message);
    }

    public ClinicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
