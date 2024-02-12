package pet.peranner.service;

import java.util.Optional;
import pet.peranner.model.User;

public interface UserService {
    User save(User user);

    Optional<User> findByEmail(String email);

    User updateRole(String email, User.Role role);

    User update(User user);

    boolean isPresentByEmail(String email);
}
