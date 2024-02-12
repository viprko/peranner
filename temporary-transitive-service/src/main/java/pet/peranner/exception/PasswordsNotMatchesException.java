package pet.peranner.exception;

public class PasswordsNotMatchesException extends RuntimeException {
    public PasswordsNotMatchesException(String message) {
        super(message);
    }
}
