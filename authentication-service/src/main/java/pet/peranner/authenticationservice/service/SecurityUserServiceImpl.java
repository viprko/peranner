package pet.peranner.authenticationservice.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.authenticationservice.exception.UserAlreadyExistException;
import pet.peranner.authenticationservice.exception.UserNotFoundException;
import pet.peranner.authenticationservice.model.SecurityUser;
import pet.peranner.authenticationservice.repository.SecurityUserRepository;

@Service
@AllArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {
    private SecurityUserRepository securityUserRepository;

    @Override
    @Transactional
    public SecurityUser save(SecurityUser securityUser) throws UserAlreadyExistException {
        if (isPresentByEmail(securityUser.getEmail())) {
            throw new UserAlreadyExistException(String.format("User with email: [%s] already "
                    + "exist", securityUser.getEmail()));
        }
        return securityUserRepository.save(securityUser);
    }

    @Override
    public SecurityUser findByEmail(String email) throws UserNotFoundException {
        Optional<SecurityUser> securityUser = securityUserRepository.findByEmail(email);
        if (securityUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email: [%s] was not found",
                    email));
        }
        return securityUser.get();
    }

    @Override
    @Transactional
    public boolean updatePassword(Long userId, String currentPassword, String newPassword) {
        return securityUserRepository.updatePassword(userId, currentPassword, newPassword) == 1;
    }

    @Override
    @Transactional
    public SecurityUser updateTelegramId(String telegramId, SecurityUser securityUser) {
        securityUser.setTelegramId(telegramId);
        return securityUserRepository.save(securityUser);
    }

    @Override
    public boolean isPresentByEmail(String email) {
        return securityUserRepository.findByEmail(email).isPresent();
    }

    @Override
    public Long findUserIdByTelegramId(String telegramUserId) throws UserNotFoundException {
        return securityUserRepository.findByTelegramId(telegramUserId).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with telegram id: [%s] was not found",
                                telegramUserId)));
    }
}
