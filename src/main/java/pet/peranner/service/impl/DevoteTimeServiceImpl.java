package pet.peranner.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.DevoteTime;
import pet.peranner.repository.DevoteTimeRepository;
import pet.peranner.service.DevoteTimeService;

@Service
@AllArgsConstructor
public class DevoteTimeServiceImpl implements DevoteTimeService {
    private final DevoteTimeRepository repository;

    @Override
    public DevoteTime save(DevoteTime entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<DevoteTime> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<DevoteTime> findAllByUserEmail(String email) {
        return repository.findAllByUserEmail(email);
    }

    @Override
    public DevoteTime update(Long id, DevoteTime devoteTime) {
        DevoteTime fromDb =
                repository.findById(id).orElseThrow(() -> new NoSuchElementException("There is "
                        + "no devoted time with id: " + id));
        Optional.ofNullable(devoteTime.getTitle()).ifPresent(fromDb::setTitle);
        Optional.ofNullable(devoteTime.getTask()).ifPresent(fromDb::setTask);
        Optional.ofNullable(devoteTime.getPlace()).ifPresent(fromDb::setPlace);
        Optional.ofNullable(devoteTime.getDescription()).ifPresent(fromDb::setDescription);
        Optional.ofNullable(devoteTime.getStartTime()).ifPresent(fromDb::setStartTime);
        Optional.ofNullable(devoteTime.getFinishTime()).ifPresent(fromDb::setFinishTime);
        return repository.save(fromDb);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DevoteTime> findByPeriod(LocalDateTime periodStart, LocalDateTime periodEnd,
                                         String email) {
        return repository.findByPeriod(periodStart, periodEnd, email);
    }
}
