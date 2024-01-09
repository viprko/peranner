package pet.peranner.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DevoteTimeResponseDto {
    private Long id;
    private Long taskId;
    private Long placeId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime actualFinishTime;
    private boolean isOutdated;
    private boolean isCompleted;
}
