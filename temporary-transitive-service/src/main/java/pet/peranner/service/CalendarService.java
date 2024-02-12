package pet.peranner.service;

import java.time.LocalDate;
import java.util.List;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;

public interface CalendarService {
    List<Contribute> findContributesForPeriod(String period, User currentUser);

    List<Contribute> findContributesForCustomPeriod(LocalDate periodStart, LocalDate periodEnd,
                                                    User currentUser);
}
