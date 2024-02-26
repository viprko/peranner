package pet.peranner.contributeservice.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<E, N extends Number> {
    E save(E entity, N userId);

    Optional<E> findById(N id);

    List<E> findAllByUserId(N userId);

    E update(E e, N id);

    void deleteById(N id);
}
