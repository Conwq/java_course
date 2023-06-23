package by.htp.ex.util;

public class ErrorHandler {

    public static String extractErrorMessage(Throwable e) {
        String message = e.getMessage();
        if (message == null && e.getCause() != null || message != null && e.getCause() != null) {
            message = e.getCause().getMessage();
        }
        return message;
    }
}
