package pet.peranner.contributeservice.strategy.recurrence.impl;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.strategy.recurrence.RecurrenceStrategy;

@Service("monthly")
@AllArgsConstructor
public class MonthlyStrategy implements RecurrenceStrategy {
    private final ContributeRepository contributeRepository;

    @Override
    public Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute,
                                              Long userId) {
        if (recurrence.getMonthDays().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Recurrence pattern \"monthly\" cannot "
                            + "work without monthly days list: %s", recurrence.getMonthDays()));
        }
        Contribute templateContribute = getTemplateContribute(recurrence, contribute, userId);
        LocalDate currentLoopDate = contribute.getStartTime().toLocalDate();
        while (currentLoopDate.isBefore(recurrence.getDueDate())) {
            for (Integer dayOfMonth : recurrence.getMonthDays()) {
                currentLoopDate = currentLoopDate.withDayOfMonth(dayOfMonth);
                if (currentLoopDate.isAfter(recurrence.getDueDate())) {
                    break;
                }
                Contribute nextContribute = templateContribute.clone();
                setStartAndFinishTimeToContribute(nextContribute, currentLoopDate,
                        contribute.getStartTime().toLocalTime(),
                        contribute.getFinishTime().toLocalTime());
                contributeRepository.save(nextContribute);
            }
        }
        return setStartAndFinishTimeToContribute(templateContribute, currentLoopDate,
                contribute.getStartTime().toLocalTime(), contribute.getFinishTime().toLocalTime());
    }
}
