package pet.peranner.telegrambot.strategy.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import pet.peranner.telegrambot.dto.response.ContributeResponseDto;
import pet.peranner.telegrambot.strategy.BotCommandHandler;

@Service("/schedule-today")
@AllArgsConstructor
public class ScheduleTodayCommandHandler implements BotCommandHandler {
    private static final String CONTRIBUTE_SERVICE_URI =
            "/contribute-service/schedule?period=today";
    private final RestTemplate restTemplate;
    private final String apiGatewayUrl;

    @Override
    public SendMessage handleBotCommand(Update update, Long userId) {
        HttpEntity<Long> httpEntity = new HttpEntity<>(userId);
        List<ContributeResponseDto> schedule =
                restTemplate.exchange(apiGatewayUrl + CONTRIBUTE_SERVICE_URI, HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<List<ContributeResponseDto>>() {
                        }).getBody();
        StringBuilder messageBuilder = new StringBuilder();
        if (schedule != null && !schedule.isEmpty()) {
            messageBuilder.append("Schedule for today:").append(System.lineSeparator());
            schedule
                    .forEach(contribute -> messageBuilder
                            .append(contribute.getStartTime().toLocalTime())
                            .append(" - ")
                            .append(contribute.getFinishTime().toLocalTime())
                            .append(" : ")
                            .append(contribute.getTitle())
                            .append(System.lineSeparator()));
        }
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(messageBuilder.toString()).build();
    }
}
