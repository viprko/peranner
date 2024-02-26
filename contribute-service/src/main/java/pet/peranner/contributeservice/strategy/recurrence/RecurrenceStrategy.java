package pet.peranner.contributeservice.strategy.recurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;

public interface RecurrenceStrategy {
    Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute, Long userId);

    default Contribute getTemplateContribute(Recurrence recurrence, Contribute contribute,
                                             Long userId) {
        Contribute templateContribute = new Contribute();
        templateContribute.setUserId(userId);
        templateContribute.setTitle(contribute.getTitle());
        Optional.ofNullable(contribute.getDescription())
                .ifPresent(templateContribute::setDescription);
        templateContribute.setRecurrence(recurrence);
        templateContribute.setCategories(contribute.getCategories());
        return templateContribute;
    }

    default Contribute setStartAndFinishTimeToContribute(Contribute contribute, LocalDate lastDate,
                                                         LocalTime startTime,
                                                         LocalTime finishTime) {
        contribute.setStartTime(LocalDateTime.of(lastDate, startTime));
        contribute.setFinishTime(LocalDateTime.of(lastDate, finishTime));
        return contribute;
    }
}
