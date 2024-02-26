package pet.peranner.authenticationservice.dto.response;

import lombok.Data;

@Data
public class SecurityUserResponseDto {
    private Long id;
    private String email;
    private String telegramId;
}
