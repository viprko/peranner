package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.DevoteTimeRequestDto;
import pet.peranner.dto.response.DevoteTimeResponseDto;
import pet.peranner.model.DevoteTime;

@Mapper(config = MapperConfig.class)
public interface DevoteTimeMapper {
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "place.id", target = "placeId")
    public DevoteTimeResponseDto toDto(DevoteTime devoteTime);

    public DevoteTime toEntity(DevoteTimeRequestDto requestDto);
}
