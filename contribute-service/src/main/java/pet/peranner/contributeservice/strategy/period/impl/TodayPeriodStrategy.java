package pet.peranner.contributeservice.strategy.period.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.period.PeriodStrategy;

@Service("today")
@AllArgsConstructor
public class TodayPeriodStrategy implements PeriodStrategy {
    private final ContributeRepository contributeRepository;

    @Override
    public List<Contribute> fetchDevoteTimeForPeriod(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime periodStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime periodEnd = LocalDateTime.now().toLocalDate().atTime(23, 59, 59);
        return contributeRepository.findByPeriod(periodStart, periodEnd, userId);
    }
}
