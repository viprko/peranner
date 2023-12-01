package pet.peranner.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.peranner.annotation.CurrentUser;
import pet.peranner.dto.request.TaskRequestDto;
import pet.peranner.dto.response.TaskResponseDto;
import pet.peranner.model.Task;
import pet.peranner.model.User;
import pet.peranner.service.TaskService;
import pet.peranner.service.mapper.TaskMapper;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("/save")
    @CurrentUser
    public ResponseEntity<TaskResponseDto> save(@RequestBody TaskRequestDto requestDto,
                                                User currentUser) {
        Task task = taskMapper.toEntity(requestDto);
        task.setUser(currentUser);
        return ResponseEntity.ok(taskMapper.toDto(taskService.save(task)));
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<TaskResponseDto>> saveAll(
            @RequestBody List<TaskRequestDto> requestDtoList) {
        List<TaskResponseDto> tasks = taskService.saveAll(requestDtoList
                        .stream()
                        .map(taskMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable("id") Long id) {
        TaskResponseDto task = taskMapper.toDto(taskService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no task with id: " + id)));
        return ResponseEntity.ok(task);
    }

    @GetMapping
    @CurrentUser
    public ResponseEntity<List<TaskResponseDto>> getAllByUserEmail(User currentUser) {
        String userEmail = currentUser.getEmail();
        List<TaskResponseDto> tasks = taskService.findAllByUserEmail(userEmail).stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-title")
    @CurrentUser
    public ResponseEntity<List<TaskResponseDto>> findByTitle(@RequestParam String substring,
                                                             User currentUser) {
        List<TaskResponseDto> tasks = taskService.findByTitle(currentUser, substring)
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-description")
    @CurrentUser
    public ResponseEntity<List<TaskResponseDto>> findByDescription(@RequestParam String substring,
                                                                   User currentUser) {
        List<TaskResponseDto> tasks = taskService.findByDescription(currentUser, substring)
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id,
                                                  @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto task =
                taskMapper.toDto(taskService.update(id, taskMapper.toEntity(taskRequestDto)));
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/complete")
    public ResponseEntity<TaskResponseDto> complete(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto task =
                taskMapper.toDto(taskService.completeTask(taskMapper.toEntity(taskRequestDto)));
        return ResponseEntity.ok(task);
    }

    @PostMapping("/reject")
    public ResponseEntity<TaskResponseDto> reject(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto task =
                taskMapper.toDto(taskService.rejectTask(taskMapper.toEntity(taskRequestDto)));
        return ResponseEntity.ok(task);
    }
}
