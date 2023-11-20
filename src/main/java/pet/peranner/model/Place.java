package pet.peranner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "places")
@Getter
@Setter
public class Place {
    @Id
    private String id;
    private String coordinates;
    private String title;
}
