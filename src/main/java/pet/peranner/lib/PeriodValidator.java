package pet.peranner.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pet.peranner.strategy.period.PeriodStrategy;

@Component
@RequiredArgsConstructor
@Slf4j
public class PeriodValidator implements ConstraintValidator<ValidPeriod, String> {
    private final List<PeriodStrategy> periodStrategyList;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regexBuilder());
    }

    private String regexBuilder() {
        StringBuilder builder = new StringBuilder();
        builder.append("^");
        periodStrategyList.stream()
                .map(periodStrategy -> periodStrategy.getClass().getAnnotation(Service.class)
                        .value())
                .forEach(string -> builder.append(string).append("|"));
        builder.deleteCharAt(-1).append("$");
        log.info("Period validator has the strategies list: ▼▼▼" + System.lineSeparator() + "{}",
                builder);
        return builder.toString();
    }
}
