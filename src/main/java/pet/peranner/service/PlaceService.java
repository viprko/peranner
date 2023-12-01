package pet.peranner.service;

import java.util.List;
import pet.peranner.model.Place;

public interface PlaceService extends BaseService<Place, Long> {
    List<Place> findByTitle(String title);
}
