package pet.peranner.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
}
