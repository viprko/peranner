package pet.peranner.userprofileservice.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.userprofileservice.exception.UserProfileAlreadyExistException;
import pet.peranner.userprofileservice.exception.UserProfileNotFoundException;
import pet.peranner.userprofileservice.model.UserProfile;
import pet.peranner.userprofileservice.repository.UserProfileRepository;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfile save(UserProfile userProfile) throws UserProfileAlreadyExistException {
        if (isPresentById(userProfile.getId())) {
            throw new UserProfileAlreadyExistException(String.format(
                    "Can't save new user profile. User profile with id: [%d] already exist",
                    userProfile.getId()));
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findById(Long id) throws UserProfileNotFoundException {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isEmpty()) {
            throw new UserProfileNotFoundException(
                    String.format("User profile with id: [%s] was not found", id));
        }
        return userProfile.get();
    }

    @Override
    public UserProfile updateInfo(UserProfile userProfile) {
        UserProfile userProfileFromDb = findById(userProfile.getId());
        Optional.ofNullable(userProfile.getBirthdate()).ifPresent(userProfileFromDb::setBirthdate);
        Optional.ofNullable(userProfile.getFirstName()).ifPresent(userProfileFromDb::setFirstName);
        Optional.ofNullable(userProfile.getLastName()).ifPresent(userProfileFromDb::setLastName);
        return save(userProfileFromDb);
    }

    private boolean isPresentById(Long id) {
        return userProfileRepository.findById(id).isPresent();
    }
}
