package modula.platform.yandex.tracker.token.exception;

public class TokenNotReceivedException extends RuntimeException {
    public TokenNotReceivedException(String message) {
        super(message);
    }

    public TokenNotReceivedException(String message, Throwable cause) {
        super(message, cause);
    }
}
