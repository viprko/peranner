package pet.peranner.contributeservice.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.contributeservice.config.MapperConfig;
import pet.peranner.contributeservice.dto.request.RecurrenceRequestDto;
import pet.peranner.contributeservice.model.Recurrence;

@Mapper(config = MapperConfig.class)
public interface RecurrenceMapper {

    Recurrence toEntity(RecurrenceRequestDto requestDto);
}
