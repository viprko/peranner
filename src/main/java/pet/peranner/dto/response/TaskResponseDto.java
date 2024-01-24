package pet.peranner.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private boolean isCompleted;
    private Long userId;
}
