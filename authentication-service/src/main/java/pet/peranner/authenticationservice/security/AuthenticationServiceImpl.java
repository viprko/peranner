package pet.peranner.authenticationservice.security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pet.peranner.authenticationservice.dto.request.SecurityUserRegistrationDto;
import pet.peranner.authenticationservice.exception.AuthenticationException;
import pet.peranner.authenticationservice.exception.UserAlreadyExistException;
import pet.peranner.authenticationservice.exception.UserNotFoundException;
import pet.peranner.authenticationservice.model.SecurityUser;
import pet.peranner.authenticationservice.service.SecurityUserService;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final SecurityUserService securityUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SecurityUser register(SecurityUserRegistrationDto securityUserRegistrationDto) {
        if (securityUserService.isPresentByEmail(securityUserRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException(String.format("User with email: [%s] already "
                    + "exist", securityUserRegistrationDto.getEmail()));
        }
        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail(securityUserRegistrationDto.getEmail());
        securityUser
                .setPassword(passwordEncoder.encode(securityUserRegistrationDto.getPassword()));
        return securityUserService.save(securityUser);
    }

    @Override
    public SecurityUser login(String email, String password) throws AuthenticationException {
        try {
            SecurityUser securityUser = securityUserService.findByEmail(email);
            if (!passwordEncoder.matches(password, securityUser.getPassword())) {
                throw new AuthenticationException("Incorrect user email or password");
            }
            return securityUser;
        } catch (UserNotFoundException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("Failed to authenticate user");
        }
    }

    @Override
    public boolean updatePassword(Long userId, String currentPassword, String newPassword) {
        return securityUserService.updatePassword(userId,
                passwordEncoder.encode(currentPassword),
                passwordEncoder.encode(newPassword));
    }
}
