package pet.peranner.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.strategy.recurrence.RecurrenceStrategy;

@Service("daily")
@AllArgsConstructor
public class DailyStrategy implements RecurrenceStrategy {
    private static final int DAYS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              User user) {
        Contribute templateContribute = getTemplateContribute(recurrence, contribute, user);
        LocalDate currentLoopDate = LocalDate.now();
        while (currentLoopDate.isBefore(recurrence.getDueDate())) {
            Contribute nextContribute = templateContribute.clone();
            setStartAndFinishTimeToContribute(nextContribute, currentLoopDate,
                    contribute.getStartTime().toLocalTime(),
                    contribute.getFinishTime().toLocalTime());
            contributeRepository.save(nextContribute);
            currentLoopDate = currentLoopDate.plusDays(DAYS_TO_ADD);
        }
        return setStartAndFinishTimeToContribute(templateContribute, currentLoopDate,
                contribute.getStartTime().toLocalTime(), contribute.getFinishTime().toLocalTime());
    }
}
