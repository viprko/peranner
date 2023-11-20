package pet.peranner.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import pet.peranner.lib.ValidEmail;

@Data
public class UserLoginDto {
    @ValidEmail
    private String email;
    @NotBlank(message = "Password can't be blank")
    private String password;
}
