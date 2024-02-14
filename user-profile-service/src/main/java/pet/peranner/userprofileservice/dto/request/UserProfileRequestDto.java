package pet.peranner.userprofileservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Data;
import pet.peranner.userprofileservice.lib.ValidBirthdate;

@Data
public class UserProfileRequestDto {
    @NotNull
    @Positive
    private Long id;
    private String firstName;
    private String lastName;
    @ValidBirthdate
    private LocalDate birthdate;
}
