package pet.peranner.contributeservice.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.recurrence.RecurrenceStrategy;

@Service("daily")
@AllArgsConstructor
public class DailyStrategy implements RecurrenceStrategy {
    private static final int DAYS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              Long userId) {
        Contribute templateContribute = getTemplateContribute(recurrence, contribute, userId);
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
