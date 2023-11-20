package pet.peranner.security;

import pet.peranner.dto.request.UserRegistrationDto;
import pet.peranner.exception.AuthenticationException;
import pet.peranner.model.User;

public interface AuthenticationService {
    User register(UserRegistrationDto user);

    User login(String login, String password) throws AuthenticationException;
}
