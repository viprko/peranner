package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.DevoteTimeRequestDto;
import pet.peranner.dto.response.DevoteTimeResponseDto;
import pet.peranner.model.DevoteTime;

@Mapper(config = MapperConfig.class)
public interface DevoteTimeMapper {
    public DevoteTimeResponseDto toDto(DevoteTime devoteTime);

    public DevoteTime toEntity(DevoteTimeRequestDto requestDto);
}
