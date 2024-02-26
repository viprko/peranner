package pet.peranner.contributeservice.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.contributeservice.config.MapperConfig;
import pet.peranner.contributeservice.dto.request.ContributeRequestDto;
import pet.peranner.contributeservice.dto.response.ContributeResponseDto;
import pet.peranner.contributeservice.model.Contribute;

@Mapper(config = MapperConfig.class)
public interface ContributeMapper {

    ContributeResponseDto toDto(Contribute contribute);

    Contribute toEntity(ContributeRequestDto requestDto);
}
