package pet.peranner.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pet.peranner.model.User;
import pet.peranner.service.UserService;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            return withUsername(email)
                    .password(userOptional.get().getPassword())
                    .roles(userOptional.get().getRole().name())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
