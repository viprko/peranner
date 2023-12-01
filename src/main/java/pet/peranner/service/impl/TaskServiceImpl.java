package pet.peranner.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.model.Task;
import pet.peranner.model.User;
import pet.peranner.repository.TaskRepository;
import pet.peranner.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public List<Task> saveAll(List<Task> taskList) {
        for (Task task : taskList) {
            save(task);
        }
        return taskList;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findByTitle(User user, String titleSubstring) {
        return taskRepository.findByTitle(user, titleSubstring);
    }

    @Override
    public List<Task> findByDescription(User user, String descriptionSubstring) {
        return taskRepository.findByDescription(user, descriptionSubstring);
    }

    @Override
    public List<Task> findAllByUserEmail(String email) {
        return taskRepository.findAllByUserEmail(email);
    }

    @Override
    public Task update(Long id, Task task) {
        Task taskFromDb =
                taskRepository.findById(task.getId()).orElseThrow(() -> new NoSuchElementException(
                        "Task with id: " + task.getId() + " doesn't exist"));
        Optional.ofNullable(task.getTitle()).ifPresent(taskFromDb::setTitle);
        Optional.ofNullable(task.getDescription()).ifPresent(taskFromDb::setDescription);
        Optional.ofNullable(task.getDueDate()).ifPresent(taskFromDb::setDueDate);
        return taskRepository.save(taskFromDb);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Task completeTask(Task task) {
        return null;
    }

    @Override
    @Transactional
    public Task rejectTask(Task task) {
        return null;
    }
}
