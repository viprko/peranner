package pet.peranner.telegrambot.security;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String BIND_TELEGRAM_ID_URI = "http://localhost:4200/telegram/bind"
            + "?telegramId=";

    @Override
    public SendMessage bindTelegramIdToUser(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(BIND_TELEGRAM_ID_URI + update.getMessage().getFrom().getId())
                .build();
    }
}
