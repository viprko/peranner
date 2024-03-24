package pet.peranner.authenticationservice.dto.request;

import lombok.Data;
import pet.peranner.authenticationservice.lib.ValidEmail;
import pet.peranner.authenticationservice.lib.ValidPassword;

@Data
@ValidPassword
public class SecurityUserRegistrationDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
    private String telegramId;
}
