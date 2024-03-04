package pet.peranner.telegrambot.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ContributeResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private List<String> categories;
}
