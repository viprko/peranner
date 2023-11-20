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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateRole(Long id, User.Role role) {
        User userFromDb =
                userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot "
                        + "find user with id: " + id));
        userFromDb.setRole(role);
        return userFromDb;
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        User userFromDb =
                userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException(
                        "Cannot find user with id: " + user.getId()));
        userFromDb
                .setAge(user.getAge())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
        return save(userFromDb);
    }

    @Override
    public boolean isPresentByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
