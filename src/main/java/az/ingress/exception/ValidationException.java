package az.ingress.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message, Object... args) {
        super(message.formatted(args));
    }
}