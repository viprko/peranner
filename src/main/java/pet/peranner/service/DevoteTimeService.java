package pet.peranner.service;

import java.time.LocalDateTime;
import java.util.List;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;

public interface DevoteTimeService extends BaseService<DevoteTime, Long> {
    List<DevoteTime> findByPeriod(LocalDateTime periodStart, LocalDateTime periodEnd,
                                  User currentUser);
}
