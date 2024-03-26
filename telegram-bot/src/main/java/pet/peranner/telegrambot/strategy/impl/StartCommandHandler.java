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
    private static final String REGISTER_LINK = "http://176.116.193.107:8085/register";
    private final TelegramBotConfig telegramBotConfig;

    @Override
    public SendMessage handleBotCommand(Update update, Long userId) {
        log.info("user id at start of method execution = {}", userId);
        Long chatId = update.getMessage().getChatId();
        String firstName = update.getMessage().getFrom().getFirstName();
        SendMessage sendMessage = new SendMessage();
        if (userId != null) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(String.format(
                    "Hello %s. Welcome to %s. Type /help if you need assistance",
                    firstName, telegramBotConfig.getBotUsername()));
        } else {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Sorry, I'm afraid I haven’t had the pleasure of meeting you yet."
                    + System.lineSeparator()
                    + "Click [here](" + REGISTER_LINK + ") to sign up to Peranner");
            sendMessage.enableMarkdown(true);
        }
        return sendMessage;
    }
}
