package pet.peranner.aspect;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pet.peranner.model.User;
import pet.peranner.service.UserService;

@Aspect
@Component
@AllArgsConstructor
public class CurrentUserAspect {
    private final UserService userService;

    @Around("@annotation(pet.peranner.annotation.CurrentUser)")
    public Object injectCurrentUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User currentUser =
                    userService.findByEmail(authentication.getName()).orElseThrow(
                            () -> new NoSuchElementException(
                                    "User with email: " + authentication.getName() + " not found"));
            Object[] args = Arrays.stream(joinPoint.getArgs())
                    .map(arg -> arg instanceof User ? currentUser : arg)
                    .toArray();
            return joinPoint.proceed(args);
        }
        throw new IllegalStateException("No authenticated user found");
    }
}
