package pet.peranner.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, I> {
    E save(E entity);

    Optional<E> findById(I id);

    List<E> findAllByUserEmail(String email);

    E update(I id, E e);

    void deleteById(I id);
}
