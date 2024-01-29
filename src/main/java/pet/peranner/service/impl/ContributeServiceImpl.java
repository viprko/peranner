package pet.peranner.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;
import pet.peranner.repository.ContributeRepository;
import pet.peranner.service.ContributeService;

@Service
@AllArgsConstructor
public class ContributeServiceImpl implements ContributeService {
    private final ContributeRepository repository;

    @Override
    public Contribute save(Contribute entity) {
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
}
