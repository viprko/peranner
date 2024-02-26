package pet.peranner.authenticationservice.dto.request;

import lombok.Data;
import pet.peranner.authenticationservice.lib.ValidPassword;

@Data
@ValidPassword
public class PasswordUpdateDto {
    private String oldPassword;
    private String password;
    private String repeatPassword;
}
