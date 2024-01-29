package pet.peranner.strategy.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.strategy.PeriodStrategy;

@Service("month")
@AllArgsConstructor
public class MonthPeriodStrategy implements PeriodStrategy {
    private static final int PERIOD_DURATION_IN_MONTHS = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public List<Contribute> fetchDevoteTimeForPeriod(User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = periodStart.plusMonths(PERIOD_DURATION_IN_MONTHS);
        return contributeRepository.findByPeriod(periodStart, periodEnd, currentUser);
    }
}
