package pet.peranner.contributeservice.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.recurrence.RecurrenceStrategy;

@Service("yearly")
@AllArgsConstructor
public class YearlyStrategy implements RecurrenceStrategy {
    private static final int YEARS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              Long userId) {
        Contribute nextContribute = getTemplateContribute(recurrence, contribute, userId);
        LocalDate localDate = contribute.getStartTime().toLocalDate();
        while (localDate.isBefore(recurrence.getDueDate())) {
            setStartAndFinishTimeToContribute(nextContribute,
                    localDate,
                    contribute.getStartTime().toLocalTime(),
                    contribute.getFinishTime().toLocalTime());
            contributeRepository.save(nextContribute);
            localDate = localDate.plusYears(YEARS_TO_ADD);
        }
        return nextContribute;
    }
}
