package pet.peranner.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Recurrence;
import pet.peranner.repository.RecurrenceRepository;
import pet.peranner.service.RecurrenceService;

@Service
@AllArgsConstructor
public class RecurrenceServiceImpl implements RecurrenceService {
    private final RecurrenceRepository recurrenceRepository;

    @Override
    public Recurrence save(Recurrence recurrence) {
        return recurrenceRepository.save(recurrence);
    }

    @Override
    public void delete(Long id) {
        recurrenceRepository.deleteById(id);
    }
}
