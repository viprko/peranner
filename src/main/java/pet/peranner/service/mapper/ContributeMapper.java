package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.ContributeRequestDto;
import pet.peranner.dto.response.ContributeResponseDto;
import pet.peranner.model.Contribute;

@Mapper(config = MapperConfig.class)
public interface ContributeMapper {

    public ContributeResponseDto toDto(Contribute contribute);

    public Contribute toEntity(ContributeRequestDto requestDto);
}
