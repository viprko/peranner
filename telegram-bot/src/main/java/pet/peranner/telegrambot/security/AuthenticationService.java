package pet.peranner.telegrambot.security;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface AuthenticationService {
    SendMessage bindTelegramIdToUser(Update update);
}
