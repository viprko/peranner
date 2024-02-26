package pet.peranner.authenticationservice.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pet.peranner.authenticationservice.exception.UserNotFoundException;
import pet.peranner.authenticationservice.model.SecurityUser;
import pet.peranner.authenticationservice.service.SecurityUserService;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SecurityUserService securityUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            SecurityUser securityUser = securityUserService.findByEmail(email);
            return withUsername(email)
                    .password(securityUser.getPassword())
                    .build();
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    }
}
