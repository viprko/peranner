package pet.peranner.contributeservice.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.service.RecurrenceService;

@Service
@AllArgsConstructor
public class RecurrenceServiceImpl implements RecurrenceService {

    @Override
    public Recurrence save(Recurrence entity, Long userId) {
        return null;
    }

    @Override
    public Optional<Recurrence> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Recurrence> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public Recurrence update(Recurrence recurrence, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
