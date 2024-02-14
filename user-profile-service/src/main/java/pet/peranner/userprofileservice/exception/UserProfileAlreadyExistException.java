package pet.peranner.userprofileservice.exception;

public class UserProfileAlreadyExistException extends RuntimeException {
    public UserProfileAlreadyExistException(String message) {
        super(message);
    }
}
