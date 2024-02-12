package pet.peranner.telegrambot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class AppTelegramBot extends TelegramLongPollingBot {

    public AppTelegramBot(@Value("${telegram.bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String answer = "Hello bliat', nareshti ty";
            Long chatId = update.getMessage().getChatId();
            String firstName = update.getMessage().getFrom().getFirstName();
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(chatId)
                    .text(answer + firstName + " tut!")
                    .build();
            try {
                sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException("Can't send message: " + sendMessage, e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "PerannerBot";
    }
}
