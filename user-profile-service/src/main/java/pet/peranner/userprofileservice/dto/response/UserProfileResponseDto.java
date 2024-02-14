package pet.peranner.userprofileservice.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserProfileResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
