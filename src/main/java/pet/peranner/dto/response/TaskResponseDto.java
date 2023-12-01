package pet.peranner.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
    private Long userId;
    private int sequenceNumber;
}
