package pet.peranner.userprofileservice.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.userprofileservice.config.MapperConfig;
import pet.peranner.userprofileservice.dto.request.UserProfileRequestDto;
import pet.peranner.userprofileservice.dto.response.UserProfileResponseDto;
import pet.peranner.userprofileservice.model.UserProfile;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserProfile toEntity(UserProfileRequestDto userProfileRequestDto);

    UserProfileResponseDto toDto(UserProfile userProfile);
}
