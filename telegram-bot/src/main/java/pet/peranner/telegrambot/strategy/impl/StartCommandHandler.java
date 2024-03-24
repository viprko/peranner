package pet.peranner.telegrambot.strategy.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pet.peranner.telegrambot.config.TelegramBotConfig;
import pet.peranner.telegrambot.strategy.BotCommandHandler;

@Service("/start")
@AllArgsConstructor
@Slf4j
public class StartCommandHandler implements BotCommandHandler {
    private final TelegramBotConfig telegramBotConfig;

    @Override
    public SendMessage handleBotCommand(Update update, Long userId) {
        log.info("user id at start of method execution = {}", userId);
        Long chatId = update.getMessage().getChatId();
        String firstName = update.getMessage().getFrom().getFirstName();
        return userId != null
                ? SendMessage.builder()
                .chatId(chatId)
                .text(String.format(
                        "Hello %s. Welcome to %s. Type /help if you need assistance",
                        firstName, telegramBotConfig.getBotUsername()))
                .build() :
                SendMessage.builder()
                        .chatId(chatId)
                        .text("Sorry, I'm afraid I haven’t had the pleasure of meeting you yet."
                                + System.lineSeparator()
                                + "Authentication link will be here in future")
                        .build();
    }
}
