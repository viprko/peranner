package pet.peranner.authenticationservice.dto.request;

import lombok.Data;

@Data
public class TelegramUserBindingDto {
    private String email;
    private String password;
    private String telegramId;
}
