package pet.peranner.service;

import java.util.List;
import java.util.Optional;
import pet.peranner.model.User;

public interface BaseService<E, I extends Number> {
    E save(E entity);

    Optional<E> findById(I id);

    List<E> findAllByUser(User user);

    E update(I id, E e);

    void deleteById(I id);
}
