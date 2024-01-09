package pet.peranner.strategy;

import java.util.List;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;

public interface PeriodStrategy {
    List<DevoteTime> fetchDevoteTimeForPeriod(User currentUser);
}
