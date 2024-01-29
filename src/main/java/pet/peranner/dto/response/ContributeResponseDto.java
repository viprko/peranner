package pet.peranner.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import pet.peranner.model.Contribute;

@Data
public class ContributeResponseDto {
    private Long id;
    private Long placeId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime actualFinishTime;
    private boolean isOutdated;
    private boolean isCompleted;
    private List<Contribute.Category> categories;
}
