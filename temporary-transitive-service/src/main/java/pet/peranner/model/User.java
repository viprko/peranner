package pet.peranner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private short age;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER,
        ADMINISTRATOR
    }
}
