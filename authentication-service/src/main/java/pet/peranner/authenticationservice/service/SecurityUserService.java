package pet.peranner.authenticationservice.service;

import pet.peranner.authenticationservice.model.SecurityUser;

public interface SecurityUserService {
    SecurityUser save(SecurityUser securityUser);

    SecurityUser findByEmail(String email);

    boolean updatePassword(Long userId, String currentPassword, String newPassword);

    SecurityUser updateTelegramId(String telegramId);

    boolean isPresentByEmail(String email);

    Long findUserIdByTelegramId(Long telegramUserId);
}
