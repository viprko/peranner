package pet.peranner.telegrambot.init;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import pet.peranner.telegrambot.service.AppTelegramBotImpl;

@Component
@AllArgsConstructor
@Slf4j
public class TelegramBotInitializer implements CommandLineRunner {
    private final AppTelegramBotImpl appTelegramBotImpl;

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(appTelegramBotImpl);
    }
}
