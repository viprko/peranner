package pet.peranner.service;

import pet.peranner.model.Recurrence;

public interface RecurrenceService {
    Recurrence save(Recurrence recurrence);

    void delete(Long id);
}
