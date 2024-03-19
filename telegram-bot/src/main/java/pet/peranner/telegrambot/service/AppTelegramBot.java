package pet.peranner.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pet.peranner.telegrambot.config.TelegramBotConfig;
import pet.peranner.telegrambot.strategy.BotCommandHandler;

@Service
public class AppTelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig telegramBotConfig;
    private final StrategyManager strategyManager;

    @Autowired
    public AppTelegramBot(TelegramBotConfig telegramBotConfig,
                          StrategyManager strategyManager) {
        super(telegramBotConfig.getBotToken());
        this.telegramBotConfig = telegramBotConfig;
        this.strategyManager = strategyManager;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            BotCommandHandler botCommandHandler =
                    strategyManager.getCommandHandlerMap().get(update.getMessage().getText());
            SendMessage sendMessage = botCommandHandler.handleBotCommand(update, null);
            try {
                sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException("Can't send message: " + sendMessage, e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotUsername();
    }
}
