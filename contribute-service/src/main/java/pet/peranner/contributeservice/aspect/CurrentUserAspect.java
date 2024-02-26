package pet.peranner.contributeservice.aspect;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pet.peranner.contributeservice.security.JwtAuthenticationFilter;

@Aspect
@Component
@AllArgsConstructor
public class CurrentUserAspect {
    private static final String ATTRIBUTE_NAME = "userId";
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Around("@annotation(pet.peranner.contributeservice.annotation.CurrentUser)")
    public Object injectCurrentUser(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Long userId = (Long) Objects.requireNonNull(requestAttributes).getRequest()
                .getAttribute(ATTRIBUTE_NAME);
        Object[] args = Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg instanceof Long ? userId : arg)
                .toArray();
        return joinPoint.proceed(args);
    }
}
