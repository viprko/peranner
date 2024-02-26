package pet.peranner.contributeservice.service;

import java.time.LocalDate;
import java.util.List;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;

public interface ContributeService extends CrudService<Contribute, Long> {

    Contribute saveWithRecurrence(Recurrence recurrence, Contribute contribute, Long userId);

    List<Contribute> findContributesForPeriod(String period, Long userId);

    List<Contribute> findContributesForPeriod(LocalDate periodStart, LocalDate periodEnd,
                                                    Long userId);
}
