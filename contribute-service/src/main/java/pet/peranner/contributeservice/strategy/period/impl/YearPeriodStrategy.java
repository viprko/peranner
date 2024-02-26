package pet.peranner.contributeservice.strategy.period.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.period.PeriodStrategy;

@Service("year")
@AllArgsConstructor
public class YearPeriodStrategy implements PeriodStrategy {
    private static final int PERIOD_DURATION_IN_YEARS = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public List<Contribute> fetchDevoteTimeForPeriod(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = now.withDayOfYear(1).toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = periodStart.plusYears(PERIOD_DURATION_IN_YEARS);
        return contributeRepository.findByPeriod(periodStart, periodEnd, userId);
    }
}
