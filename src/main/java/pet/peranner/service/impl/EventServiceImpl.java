package pet.peranner.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.model.Event;
import pet.peranner.model.User;
import pet.peranner.repository.EventRepository;
import pet.peranner.service.EventService;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAllByUser(User user) {
        return eventRepository.getAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Event update(Long id, Event event) {
        Event eventFromDb =
                eventRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(
                                "There is no event with id: " + event.getId()));
        Optional.ofNullable(event.getTitle()).ifPresent(eventFromDb::setTitle);
        Optional.ofNullable(event.getPlace()).ifPresent(eventFromDb::setPlace);
        Optional.ofNullable(event.getDescription()).ifPresent(eventFromDb::setDescription);
        Optional.ofNullable(event.getStartTime()).ifPresent(eventFromDb::setStartTime);
        Optional.ofNullable(event.getFinishTime()).ifPresent(eventFromDb::setFinishTime);
        Optional.ofNullable(event.getActualFinishTime())
                .ifPresent(eventFromDb::setActualFinishTime);
        return eventRepository.save(eventFromDb);
    }
}
