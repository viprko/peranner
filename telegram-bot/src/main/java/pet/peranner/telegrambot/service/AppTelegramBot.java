package pet.peranner.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pet.peranner.telegrambot.config.TelegramBotConfig;
import pet.peranner.telegrambot.security.AuthenticationService;
import pet.peranner.telegrambot.strategy.BotCommandHandler;

@Service
public class AppTelegramBot extends TelegramLongPollingBot {
    private static final String AUTH_COMMAND = "/itsme";
    private final AuthenticationService authenticationService;
    private final TelegramBotConfig telegramBotConfig;
    private final StrategyManager strategyManager;

    @Autowired
    public AppTelegramBot(TelegramBotConfig telegramBotConfig,
                          StrategyManager strategyManager,
                          AuthenticationService authenticationService) {
        super(telegramBotConfig.getBotToken());
        this.telegramBotConfig = telegramBotConfig;
        this.strategyManager = strategyManager;
        this.authenticationService = authenticationService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage;
            if (update.getMessage().getText().equalsIgnoreCase(AUTH_COMMAND)) {
                sendMessage = authenticationService.bindTelegramIdToUser(update);
            } else {
                BotCommandHandler botCommandHandler =
                        strategyManager.getCommandHandlerMap().get(update.getMessage().getText());
                sendMessage = botCommandHandler.handleBotCommand(update, null);
            }
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
