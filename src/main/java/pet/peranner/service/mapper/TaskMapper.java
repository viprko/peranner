package pet.peranner.service.mapper;

import org.mapstruct.Mapper;
import pet.peranner.config.MapperConfig;
import pet.peranner.dto.request.TaskRequestDto;
import pet.peranner.dto.response.TaskResponseDto;
import pet.peranner.model.Task;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {
    public TaskResponseDto toDto(Task task);

    public Task toEntity(TaskRequestDto requestDto);
}
