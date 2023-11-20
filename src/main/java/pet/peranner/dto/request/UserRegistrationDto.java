package pet.peranner.dto.request;

import lombok.Data;
import pet.peranner.lib.ValidEmail;
import pet.peranner.lib.ValidPassword;

@Data
@ValidPassword(message = "Passwords doesn't match")
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private short age;
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
}
