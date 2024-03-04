package pet.peranner.telegrambot.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pet.peranner.telegrambot.strategy.BotCommandHandler;

@Getter
@Component
@Slf4j
public class StrategyManager {
    private final Map<String, BotCommandHandler> commandHandlerMap;

    @Autowired
    public StrategyManager(List<BotCommandHandler> commandHandlerList) {
        commandHandlerMap = commandHandlerList.stream()
                .collect(Collectors.toMap(
                        strategy -> AopProxyUtils.ultimateTargetClass(strategy)
                                .getAnnotation(Service.class).value(), Function.identity()));
    }
}
