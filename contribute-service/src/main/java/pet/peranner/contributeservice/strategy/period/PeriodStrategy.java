package pet.peranner.contributeservice.strategy.period;

import java.util.List;
import pet.peranner.contributeservice.model.Contribute;

public interface PeriodStrategy {
    List<Contribute> fetchDevoteTimeForPeriod(Long userId);
}
