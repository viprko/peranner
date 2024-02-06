package pet.peranner.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.strategy.recurrence.RecurrenceStrategy;

@Service("yearly")
@AllArgsConstructor
public class YearlyStrategy implements RecurrenceStrategy {
    private static final int YEARS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              User user) {
        Contribute nextContribute = getTemplateContribute(recurrence, contribute, user);
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
