package pet.peranner.userprofileservice.service;

import pet.peranner.userprofileservice.model.UserProfile;

public interface UserProfileService {
    UserProfile save(UserProfile userProfile);

    UserProfile findById(Long id);

    UserProfile updateInfo(UserProfile userProfile);
}
