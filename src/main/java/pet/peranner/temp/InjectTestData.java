package pet.peranner.temp;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pet.peranner.dto.request.UserRegistrationDto;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.Task;
import pet.peranner.security.AuthenticationService;
import pet.peranner.service.DevoteTimeService;
import pet.peranner.service.TaskService;
import pet.peranner.service.UserService;
import pet.peranner.service.mapper.UserMapper;

@Component
@AllArgsConstructor
public class InjectTestData implements CommandLineRunner {
    private final AuthenticationService authenticationService;
    private final TaskService taskService;
    private final DevoteTimeService devoteTimeService;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setEmail("test@test.com");
        user.setPassword("12345678");
        user.setRepeatPassword("12345678");
        if (!userService.isPresentByEmail(user.getEmail())) {
            authenticationService.register(user);
        }

        Task task = new Task();
        task.setTitle("body refactoring");
        task.setStartDate(LocalDate.of(2024, 1, 9));
        task.setDueDate(LocalDate.of(2024, 6, 1));
        task.setUser(userMapper.toEntity(user));
        taskService.save(task);

        for (int i = 0; i < 8; i++) {
            DevoteTime devoteTime = new DevoteTime();
            devoteTime.setUser(userMapper.toEntity(user));
            devoteTime.setTitle("push ups");
            devoteTime.setStartTime(LocalDate.now().atTime(12, 0, 0).plusDays(i));
            devoteTime.setFinishTime(devoteTime.getStartTime().plusHours(2));
            devoteTime.setTask(task);
            devoteTimeService.save(devoteTime);
        }
    }
}
