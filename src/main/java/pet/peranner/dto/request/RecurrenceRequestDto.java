package pet.peranner.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;

@Data
public class RecurrenceRequestDto {
    @NotNull
    private String title;
    private String description;
    @NotNull
    private Recurrence.RecurrencePattern recurrencePattern;
    @NotNull
    private LocalDate dueDate;
    private List<DayOfWeek> weeklyDays;
    private List<Integer> monthlyDate;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime finishTime;
    private List<Contribute.Category> categories;

}
