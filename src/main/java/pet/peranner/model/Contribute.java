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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contributes")
@Getter
@Setter
public class Contribute {
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime actualFinishTime;
    private boolean isCompleted;
    private boolean isOutdated;
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "contribute_categories", joinColumns = @JoinColumn(name =
            "contribute_id"))
    @Column(name = "category")
    private List<Category> categories;

    public enum Category {
        ROUTINE,
        SPORT,
        STUDY,
        JOB,
        FAMILY,
        VACATION,
        OTHER,
        HEALTH,
        SOCIAL,
        ENTERTAINMENT,
    }
}
