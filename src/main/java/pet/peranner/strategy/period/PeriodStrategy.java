package pet.peranner.strategy.period;

import java.util.List;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;

public interface PeriodStrategy {
    List<Contribute> fetchDevoteTimeForPeriod(User currentUser);
}
