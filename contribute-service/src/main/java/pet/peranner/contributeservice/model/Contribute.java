package pet.peranner.contributeservice.model;

import jakarta.persistence.CascadeType;
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
import org.mapstruct.control.DeepClone;

@Entity
@Table(name = "contributes")
@Getter
@Setter
@DeepClone
public class Contribute implements Cloneable {
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recurrence_id")
    private Recurrence recurrence;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "contribute_categories", joinColumns = @JoinColumn(name =
            "contribute_id"))
    @Column(name = "category")
    private List<Category> categories;

    @Override
    public Contribute clone() {
        try {
            return (Contribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

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
