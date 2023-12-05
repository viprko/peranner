package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.DevoteTimeRequestDto;
import pet.peranner.dto.request.PlaceRequestDto;
import pet.peranner.dto.response.DevoteTimeResponseDto;
import pet.peranner.dto.response.PlaceResponseDto;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.Place;

@Mapper(config = MapperConfig.class)
public interface DevoteTimeMapper {
    public DevoteTimeResponseDto toDto(DevoteTime devoteTime);

    public DevoteTime toEntity(DevoteTimeRequestDto requestDto);
}
