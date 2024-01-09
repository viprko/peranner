package pet.peranner.strategy.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;
import pet.peranner.repository.DevoteTimeRepository;
import pet.peranner.strategy.PeriodStrategy;

@Service("year")
@AllArgsConstructor
public class YearPeriodStrategy implements PeriodStrategy {
    private static final int PERIOD_DURATION_IN_YEARS = 1;
    private final DevoteTimeRepository devoteTimeRepository;

    @Override
    public List<DevoteTime> fetchDevoteTimeForPeriod(User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = now.withDayOfYear(1).toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = periodStart.plusYears(PERIOD_DURATION_IN_YEARS);
        return devoteTimeRepository.findByPeriod(periodStart, periodEnd, currentUser.getEmail());
    }
}
