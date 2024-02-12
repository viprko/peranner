package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.RecurrenceRequestDto;
import pet.peranner.model.Recurrence;

@Mapper(config = MapperConfig.class)
public interface RecurrenceMapper {

    Recurrence toEntity(RecurrenceRequestDto requestDto);
}
