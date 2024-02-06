package pet.peranner.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recurrences")
@Getter
@Setter
public class Recurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RecurrencePattern recurrencePattern;
    private LocalDate dueDate;
    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "recurrence_week_days", joinColumns = @JoinColumn(name =
            "recurrence_id"))
    @Column(name = "week_day")
    private List<DayOfWeek> weekDays;
    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "recurrence_month_days", joinColumns = @JoinColumn(name =
            "recurrence_id"))
    @Column(name = "month_day")
    private List<Integer> monthDays;

    public enum RecurrencePattern {
        WEEKLY,
        MONTHLY,
        DAILY,
        WEEKDAYS,
        WEEKENDS,
        YEARLY
    }
}
