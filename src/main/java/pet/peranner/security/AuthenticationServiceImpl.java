package pet.peranner.security;

import static pet.peranner.model.User.Role;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pet.peranner.dto.request.UserRegistrationDto;
import pet.peranner.exception.AuthenticationException;
import pet.peranner.exception.UserAlreadyExistException;
import pet.peranner.model.User;
import pet.peranner.service.UserService;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Role DEFAULT_ROLE = Role.USER;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationDto userRegistrationDto) {
        if (userService.isPresentByEmail(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException("User with such email already exist");
        }
        User user = new User();
        user
                .setAge(userRegistrationDto.getAge())
                .setEmail(userRegistrationDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .setFirstName(userRegistrationDto.getFirstName())
                .setLastName(userRegistrationDto.getLastName())
                .setRole(DEFAULT_ROLE);
        return userService.save(user);
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isEmpty() || !passwordEncoder.matches(password,
                userOptional.get().getPassword())) {
            throw new AuthenticationException("Incorrect user email or password");
        }
        return userOptional.get();
    }
}
