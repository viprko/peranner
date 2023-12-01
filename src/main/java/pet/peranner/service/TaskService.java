package pet.peranner.service;

import java.util.List;
import pet.peranner.model.Task;
import pet.peranner.model.User;

public interface TaskService extends BaseService<Task, Long> {

    List<Task> saveAll(List<Task> taskList);

    List<Task> findByTitle(User user, String titleSubstring);

    List<Task> findByDescription(User user, String descriptionSubstring);

    Task completeTask(Task task);

    Task rejectTask(Task task);
}
