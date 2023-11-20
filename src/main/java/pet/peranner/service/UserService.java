package pet.peranner.service;

import java.util.Optional;
import pet.peranner.model.User;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User updateRole(Long id, User.Role role);

    User update(User user);

    boolean isPresentByEmail(String email);
}
