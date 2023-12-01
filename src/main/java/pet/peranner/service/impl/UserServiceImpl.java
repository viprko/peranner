package pet.peranner.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.User;
import pet.peranner.repository.UserRepository;
import pet.peranner.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public User updateRole(String email, User.Role role) {
        User userFromDb =
                userRepository.findById(email).orElseThrow(() -> new NoSuchElementException(
                        "Cannot "
                        + "find user with id: " + email));
        userFromDb.setRole(role);
        return userFromDb;
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        User userFromDb =
                userRepository.findById(user.getEmail())
                        .orElseThrow(() -> new NoSuchElementException(
                                "Cannot find user with email: " + user.getEmail()));
        userFromDb
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
        return save(userFromDb);
    }

    @Override
    public boolean isPresentByEmail(String email) {
        return userRepository.findById(email).isPresent();
    }
}
