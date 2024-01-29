package pet.peranner.strategy;

import java.util.List;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;

public interface PeriodStrategy {
    List<Contribute> fetchDevoteTimeForPeriod(User currentUser);
}
