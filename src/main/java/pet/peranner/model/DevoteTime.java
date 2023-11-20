package pet.peranner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devote_time")
@Getter
@Setter
public class DevoteTime {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime actualFinishTime;
}