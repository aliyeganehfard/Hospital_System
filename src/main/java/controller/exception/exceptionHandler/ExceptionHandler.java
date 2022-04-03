package controller.exception.exceptionHandler;

import controller.exception.IdException;

public class ExceptionHandler {


    public void isId(String id) {
        if (!isDigit(id))
            throw new IdException("incorrect id");
    }

    private boolean isDigit(String id) {
        for (var chr : id.toCharArray()) {
            if (!Character.isDigit(chr))
                return false;
        }
        return true;
    }
}
