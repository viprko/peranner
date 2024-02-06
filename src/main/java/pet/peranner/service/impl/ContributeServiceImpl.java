package pet.peranner.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.model.Contribute;
import pet.peranner.model.Recurrence;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.service.ContributeService;
import pet.peranner.service.RecurrenceService;
import pet.peranner.service.mapper.RecurrenceMapper;
import pet.peranner.strategy.recurrence.RecurrenceStrategy;

@Service
public class ContributeServiceImpl implements ContributeService {
    private final ContributeRepository repository;
    private final RecurrenceService recurrenceService;
    private final RecurrenceMapper recurrenceMapper;
    private final Map<String, RecurrenceStrategy> recurrenceStrategyMap;

    @Autowired
    public ContributeServiceImpl(ContributeRepository repository,
                                 RecurrenceService recurrenceService,
                                 RecurrenceMapper recurrenceMapper,
                                 List<RecurrenceStrategy> recurrenceStrategies) {
        this.repository = repository;
        this.recurrenceService = recurrenceService;
        this.recurrenceMapper = recurrenceMapper;
        this.recurrenceStrategyMap = recurrenceStrategies.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getAnnotation(Service.class).value(),
                        Function.identity()));
    }

    @Override
    @Transactional
    public Contribute save(Contribute entity, User user) {
        entity.setUser(user);
        return repository.save(entity);
    }

    @Override
    public Optional<Contribute> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Contribute> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    @Transactional
    public Contribute update(Long id, Contribute contribute) {
        Contribute fromDb =
                repository.findById(id).orElseThrow(() -> new NoSuchElementException("There is "
                        + "no devoted time with id: " + id));
        Optional.ofNullable(contribute.getTitle()).ifPresent(fromDb::setTitle);
        Optional.ofNullable(contribute.getDescription()).ifPresent(fromDb::setDescription);
        Optional.ofNullable(contribute.getStartTime()).ifPresent(fromDb::setStartTime);
        Optional.ofNullable(contribute.getFinishTime()).ifPresent(fromDb::setFinishTime);
        Optional.ofNullable(contribute.getCategories()).ifPresent(contribute::setCategories);
        return repository.save(fromDb);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Contribute> findByPeriod(LocalDateTime periodStart, LocalDateTime periodEnd,
                                         User currentUser) {
        return repository.findByPeriod(periodStart, periodEnd, currentUser);
    }

    @Override
    @Transactional
    public Contribute saveWithRecurrence(Recurrence recurrence, Contribute contribute, User user) {
        recurrenceService.save(recurrence);
        RecurrenceStrategy recurrenceStrategy =
                recurrenceStrategyMap.get(recurrence.getRecurrencePattern().name());
        return recurrenceStrategy.saveByRecurrencePattern(recurrence, contribute, user);
    }
}
