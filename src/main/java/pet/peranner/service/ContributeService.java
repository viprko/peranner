package pet.peranner.service;

import java.time.LocalDateTime;
import java.util.List;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;

public interface ContributeService extends BaseService<Contribute, Long> {
    List<Contribute> findByPeriod(LocalDateTime periodStart, LocalDateTime periodEnd,
                                  User currentUser);
}
