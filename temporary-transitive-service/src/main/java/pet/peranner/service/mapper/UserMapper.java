package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.UserRegistrationDto;
import pet.peranner.dto.response.UserResponseDto;
import pet.peranner.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserRegistrationDto requestDto);
}
