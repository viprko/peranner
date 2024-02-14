package pet.peranner.userprofileservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users_profiles")
@Getter
@Setter
public class UserProfile {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
