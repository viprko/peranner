package pet.peranner.authenticationservice.security;

import pet.peranner.authenticationservice.dto.request.SecurityUserRegistrationDto;
import pet.peranner.authenticationservice.model.SecurityUser;

public interface AuthenticationService {
    SecurityUser register(SecurityUserRegistrationDto securityUserRegistrationDto);

    SecurityUser login(String email, String password);

    boolean updatePassword(Long userId, String currentPassword, String newPassword);
}
