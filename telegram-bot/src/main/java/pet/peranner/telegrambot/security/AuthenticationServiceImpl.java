package pet.peranner.telegrambot.security;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String BIND_TELEGRAM_ID_URI = "http://176.116.193.107:8085/telegram/bind"
            + "?telegramId=";

    @Override
    public SendMessage bindTelegramIdToUser(Update update) {
        Long telegramId = update.getMessage().getFrom().getId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Click [here](" + BIND_TELEGRAM_ID_URI + telegramId
                + ") for bind your Telegram");
        return sendMessage;
    }
}
