package pet.peranner.contributeservice.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.contributeservice.model.Contribute;
import pet.peranner.contributeservice.model.Recurrence;
import pet.peranner.contributeservice.repository.ContributeRepository;
import pet.peranner.contributeservice.service.ContributeService;
import pet.peranner.contributeservice.service.RecurrenceService;
import pet.peranner.contributeservice.strategy.period.PeriodStrategy;
import pet.peranner.contributeservice.strategy.recurrence.RecurrenceStrategy;

@Service
public class ContributeServiceImpl implements ContributeService {
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);
    private final RecurrenceService recurrenceService;
    private final ContributeRepository contributeRepository;
    private final Map<String, PeriodStrategy> periodStrategyMap;
    private final Map<String, RecurrenceStrategy> recurrenceStrategyMap;

    @Autowired
    public ContributeServiceImpl(List<PeriodStrategy> periodStrategies,
                                 List<RecurrenceStrategy> recurrenceStrategies,
                                 ContributeRepository contributeRepository,
                                 RecurrenceService recurrenceService) {
        periodStrategyMap = periodStrategies.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getAnnotation(Service.class).value(),
                        Function.identity()));
        recurrenceStrategyMap = recurrenceStrategies.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getAnnotation(Service.class).value(),
                        Function.identity()));
        this.recurrenceService = recurrenceService;
        this.contributeRepository = contributeRepository;
    }

    @Override
    @Transactional
    public Contribute saveWithRecurrence(Recurrence recurrence, Contribute contribute,
                                         Long userId) {
        Recurrence savedRecurrence = recurrenceService.save(recurrence, userId);
        RecurrenceStrategy recurrenceStrategy =
                recurrenceStrategyMap.get(recurrence.getRecurrencePattern().name());
        return recurrenceStrategy.saveByRecurrencePattern(savedRecurrence, contribute, userId);
    }

    @Override
    public List<Contribute> findContributesForPeriod(String period, Long userId) {
        PeriodStrategy periodStrategy = periodStrategyMap.get(period);
        return periodStrategy.fetchDevoteTimeForPeriod(userId);
    }

    @Override
    public List<Contribute> findContributesForPeriod(LocalDate periodStart, LocalDate periodEnd,
                                                     Long userId) {
        return contributeRepository.findByPeriod(periodStart.atStartOfDay(),
                periodEnd.atTime(END_OF_DAY),
                userId);
    }

    @Override
    @Transactional
    public Contribute save(Contribute entity, Long userId) {
        entity.setUserId(userId);
        return contributeRepository.save(entity);
    }

    @Override
    public Optional<Contribute> findById(Long id) {
        return contributeRepository.findById(id);
    }

    @Override
    public List<Contribute> findAllByUserId(Long userId) {
        return contributeRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Contribute update(Contribute contribute, Long id) {
        Contribute fromDb =
                contributeRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("There is "
                                + "no devoted time with id: " + id));
        Optional.ofNullable(contribute.getTitle()).ifPresent(fromDb::setTitle);
        Optional.ofNullable(contribute.getDescription()).ifPresent(fromDb::setDescription);
        Optional.ofNullable(contribute.getStartTime()).ifPresent(fromDb::setStartTime);
        Optional.ofNullable(contribute.getFinishTime()).ifPresent(fromDb::setFinishTime);
        Optional.ofNullable(contribute.getCategories()).ifPresent(contribute::setCategories);
        return contributeRepository.save(fromDb);
    }

    @Override
    public void deleteById(Long id) {
        contributeRepository.deleteById(id);
    }
}
