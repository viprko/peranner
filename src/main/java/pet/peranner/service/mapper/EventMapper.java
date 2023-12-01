package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.EventRequestDto;
import pet.peranner.dto.response.EventResponseDto;
import pet.peranner.model.Event;

@Mapper(config = MapperConfig.class)
public interface EventMapper {
    public EventResponseDto toDto(Event event);

    public Event toEntity(EventRequestDto requestDto);
}
