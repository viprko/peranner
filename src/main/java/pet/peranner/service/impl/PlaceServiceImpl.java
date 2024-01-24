package pet.peranner.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.peranner.model.Place;
import pet.peranner.model.User;
import pet.peranner.repository.PlaceRepository;
import pet.peranner.service.PlaceService;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public Place save(Place entity) {
        return placeRepository.save(entity);
    }

    @Override
    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> findByTitle(String titleSubstring) {
        return placeRepository.findByTitle(titleSubstring);
    }

    @Override
    public List<Place> findAllByUser(User user) {
        return placeRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public Place update(Long id, Place place) {
        Place placeFromDb =
                placeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There "
                        + "is no place with id: " + id));
        Optional.ofNullable(place.getLatitude()).ifPresent(placeFromDb::setLatitude);
        Optional.ofNullable(place.getLongitude()).ifPresent(placeFromDb::setLongitude);
        Optional.ofNullable(place.getTitle()).ifPresent(placeFromDb::setTitle);
        return placeRepository.save(placeFromDb);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }
}
