package pet.peranner.contributeservice.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.recurrence.RecurrenceStrategy;

@Service("weekly")
@AllArgsConstructor
public class WeeklyStrategy implements RecurrenceStrategy {
    private static final int DAYS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              Long userId) {
        if (recurrence.getWeekDays().isEmpty()) {
            throw new IllegalArgumentException("Weekly days list shouldn't be empty");
        }
        Contribute templateContribute = getTemplateContribute(recurrence, contribute, userId);
        LocalDate currentLoopDate = contribute.getStartTime().toLocalDate();
        while (currentLoopDate.isBefore(recurrence.getDueDate())) {
            if (recurrence.getWeekDays().contains(currentLoopDate.getDayOfWeek())) {
                Contribute nextContribute = templateContribute.clone();
                setStartAndFinishTimeToContribute(nextContribute, currentLoopDate,
                        contribute.getStartTime().toLocalTime(),
                        contribute.getFinishTime().toLocalTime());
                contributeRepository.save(nextContribute);
            }
            currentLoopDate = currentLoopDate.plusDays(DAYS_TO_ADD);
        }
        return setStartAndFinishTimeToContribute(templateContribute, currentLoopDate,
                contribute.getStartTime().toLocalTime(), contribute.getFinishTime().toLocalTime());
    }
}


