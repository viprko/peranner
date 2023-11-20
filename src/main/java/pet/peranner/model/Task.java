package pet.peranner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
}
