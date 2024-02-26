package pet.peranner.authenticationservice.dto.request;

import lombok.Data;

@Data
public class SecurityUserRegistrationDto {
    private String email;
    private String password;
    private String repeatPassword;
    private String telegramId;
}
