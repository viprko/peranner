package pet.peranner.strategy.recurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;
import pet.peranner.model.User;

public interface RecurrenceStrategy {
    Contribute saveByRecurrencePattern(Recurrence recurrence, Contribute contribute, User user);

    default Contribute getTemplateContribute(Recurrence recurrence, Contribute contribute,
                                             User user) {
        Contribute templateContribute = new Contribute();
        templateContribute.setUser(user);
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
