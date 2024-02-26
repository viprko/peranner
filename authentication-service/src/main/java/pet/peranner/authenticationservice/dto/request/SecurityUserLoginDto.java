package pet.peranner.authenticationservice.dto.request;

import lombok.Data;

@Data
public class SecurityUserLoginDto {
    private String email;
    private String password;
}
