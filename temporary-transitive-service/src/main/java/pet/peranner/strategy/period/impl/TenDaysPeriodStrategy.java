package pet.peranner.strategy.period.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.strategy.period.PeriodStrategy;

@Service("ten-days")
@AllArgsConstructor
public class TenDaysPeriodStrategy implements PeriodStrategy {
    private static final int PERIOD_DURATION_IN_DAYS = 10;
    private final ContributeRepository contributeRepository;

    @Override
    public List<Contribute> fetchDevoteTimeForPeriod(User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = now.toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = periodStart.plusDays(PERIOD_DURATION_IN_DAYS);
        return contributeRepository.findByPeriod(periodStart, periodEnd, currentUser);
    }
}
