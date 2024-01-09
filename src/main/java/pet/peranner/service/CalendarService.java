package pet.peranner.service;

import java.time.LocalDate;
import java.util.List;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;

public interface CalendarService {
    List<DevoteTime> findDevoteTimeForPeriod(String period, User currentUser);

    List<DevoteTime> findDevoteTimeForCustomPeriod(LocalDate periodStart, LocalDate periodEnd,
                                                   User currentUser);
}
