package pet.peranner.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EventRequestDto {
    private Long placeId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}
