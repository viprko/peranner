package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.ContributeRequestDto;
import pet.peranner.dto.response.ContributeResponseDto;
import pet.peranner.model.Contribute;

@Mapper(config = MapperConfig.class)
public interface ContributeMapper {

    ContributeResponseDto toDto(Contribute contribute);

    Contribute toEntity(ContributeRequestDto requestDto);
}
