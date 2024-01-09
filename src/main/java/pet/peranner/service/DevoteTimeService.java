package pet.peranner.service;

import java.time.LocalDateTime;
import java.util.List;
import pet.peranner.model.DevoteTime;

public interface DevoteTimeService extends BaseService<DevoteTime, Long> {
    List<DevoteTime> findByPeriod(LocalDateTime periodStart, LocalDateTime periodEnd, String email);
}
