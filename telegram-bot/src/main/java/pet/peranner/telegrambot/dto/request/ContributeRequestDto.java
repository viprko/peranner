package pet.peranner.telegrambot.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ContributeRequestDto {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private List<String> categories;
}
