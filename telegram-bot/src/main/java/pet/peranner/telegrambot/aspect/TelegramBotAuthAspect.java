package pet.peranner.telegrambot.aspect;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

@Aspect
@Component
@RequiredArgsConstructor
public class TelegramBotAuthAspect {
    private static final String
            AUTH_SERVICE_VERIFY_TELEGRAM_USER_URI = "http://authentication-service/telegram/verify";
    private static final String PREFIX_OF_USER_ID_DEPENDENCY = "TelegramUserId";
    private static final Duration CACHE_EXPIRATION_TIME = Duration.ofMinutes(15);
    private static final Long CACHE_EXPIRATION_TIME_THRESHOLD = 1L;
    private static final String TELEGRAM_USER_HEADER = "X-Telegram-UserId";
    private final RedisTemplate<String, Long> redisTemplate;
    private final RestTemplate restTemplate;

    @Around("execution(* pet.peranner.telegrambot.strategy.*.*(..))")
    public Object checkTelegramUserId(ProceedingJoinPoint joinPoint) throws Throwable {
        Optional<Long> telegramUserId = extractTelegramUserId(joinPoint);
        if (telegramUserId.isPresent()) {
            Optional<Long> cachedUserId = getCachedUserId(telegramUserId.get());
            if (cachedUserId.isPresent()) {
                if (!isCacheBreakExpirationThreshold(telegramUserId.get())) {
                    refreshCachedUserIdExpirationTime(telegramUserId.get());
                    return proceedWithUserId(joinPoint, cachedUserId.get());
                }
            } else {
                Optional<Long> userIdFromAuthService =
                        getUserIdFromAuthService(telegramUserId.get());
                if (userIdFromAuthService.isPresent()) {
                    cacheUserId(telegramUserId.get(), userIdFromAuthService.get());
                    return proceedWithUserId(joinPoint, userIdFromAuthService.get());
                }
            }
        }
        throw new AuthenticationException("Failed to verify telegram user");
    }

    private Optional<Long> extractTelegramUserId(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Update update) {
            return Optional.of(update.getMessage().getFrom().getId());
        }
        return Optional.empty();
    }

    private Object proceedWithUserId(ProceedingJoinPoint joinPoint, Long userId)
            throws Throwable {
        Object[] args = Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg instanceof Long ? userId : arg)
                .toArray();
        return joinPoint.proceed(args);
    }

    private void cacheUserId(Long telegramUserId, Long userId) {
        String key = PREFIX_OF_USER_ID_DEPENDENCY + telegramUserId;
        redisTemplate.opsForValue().set(key, userId);
        redisTemplate.expire(key, CACHE_EXPIRATION_TIME);
    }

    private boolean isCacheBreakExpirationThreshold(Long telegramUserId) {
        String key = PREFIX_OF_USER_ID_DEPENDENCY + telegramUserId;
        Optional<Long> remainingTime = Optional.ofNullable(redisTemplate.getExpire(key));
        return remainingTime.filter(duration -> duration <= CACHE_EXPIRATION_TIME_THRESHOLD)
                .isPresent();
    }

    private void refreshCachedUserIdExpirationTime(Long telegramUserId) {
        String key = PREFIX_OF_USER_ID_DEPENDENCY + telegramUserId;
        redisTemplate.expire(key, CACHE_EXPIRATION_TIME);
    }

    private Optional<Long> getCachedUserId(Long telegramUserId) {
        String key = PREFIX_OF_USER_ID_DEPENDENCY + telegramUserId;
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    private Optional<Long> getUserIdFromAuthService(Long telegramUserId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(TELEGRAM_USER_HEADER, telegramUserId.toString());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Long> exchange =
                restTemplate.exchange(AUTH_SERVICE_VERIFY_TELEGRAM_USER_URI, HttpMethod.GET,
                        entity,
                        Long.class);
        return Optional.ofNullable(exchange.getBody());
    }
}

