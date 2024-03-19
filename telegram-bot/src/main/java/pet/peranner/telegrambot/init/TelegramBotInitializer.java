package pet.peranner.telegrambot.init;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import pet.peranner.telegrambot.service.AppTelegramBot;

@Component
@AllArgsConstructor
public class TelegramBotInitializer implements CommandLineRunner {
    private final AppTelegramBot appTelegramBot;

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(appTelegramBot);
    }
}
