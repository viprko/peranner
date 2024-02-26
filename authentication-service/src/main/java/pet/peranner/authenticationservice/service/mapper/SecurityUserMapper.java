package pet.peranner.authenticationservice.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.authenticationservice.config.MapperConfig;
import pet.peranner.authenticationservice.dto.request.SecurityUserRegistrationDto;
import pet.peranner.authenticationservice.dto.response.SecurityUserResponseDto;
import pet.peranner.authenticationservice.model.SecurityUser;

@Mapper(config = MapperConfig.class)
public interface SecurityUserMapper {
    SecurityUserResponseDto toDto(SecurityUser securityUser);

    SecurityUser toEntity(SecurityUserRegistrationDto requestDto);
}
