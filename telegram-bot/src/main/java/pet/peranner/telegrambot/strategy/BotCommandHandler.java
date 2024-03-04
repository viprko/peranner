package pet.peranner.telegrambot.strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommandHandler {
    SendMessage handleBotCommand(Update update, Long userId);
}
