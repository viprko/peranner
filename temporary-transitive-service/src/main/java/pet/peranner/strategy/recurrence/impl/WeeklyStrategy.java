package pet.peranner.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.strategy.recurrence.RecurrenceStrategy;

@Service("weekly")
@AllArgsConstructor
public class WeeklyStrategy implements RecurrenceStrategy {
    private static final int DAYS_TO_ADD = 1;
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              User user) {
        if (recurrence.getWeekDays().isEmpty()) {
            throw new IllegalArgumentException("Weekly days list shouldn't be empty");
        }
        Contribute templateContribute = getTemplateContribute(recurrence, contribute, user);
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


