package pet.peranner.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramBotService {
    SendMessage responseMessage(String message, String chatId);
}
