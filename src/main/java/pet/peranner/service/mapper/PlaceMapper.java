package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.PlaceRequestDto;
import pet.peranner.dto.response.PlaceResponseDto;
import pet.peranner.model.Place;

@Mapper(config = MapperConfig.class)
public interface PlaceMapper {
    public PlaceResponseDto toDto(Place place);

    public Place toEntity(PlaceRequestDto requestDto);
}
