package pet.peranner.strategy.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;
import pet.peranner.repository.DevoteTimeRepository;
import pet.peranner.strategy.PeriodStrategy;

@Service("ten-days")
@AllArgsConstructor
public class TenDaysPeriodStrategy implements PeriodStrategy {
    private static final int PERIOD_DURATION_IN_DAYS = 10;
    private final DevoteTimeRepository devoteTimeRepository;

    @Override
    public List<DevoteTime> fetchDevoteTimeForPeriod(User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = now.toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = periodStart.plusDays(PERIOD_DURATION_IN_DAYS);
        return devoteTimeRepository.findByPeriod(periodStart, periodEnd, currentUser.getEmail());
    }
}