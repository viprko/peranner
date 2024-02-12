package pet.peranner.service;

import java.util.List;
import java.util.Optional;
import pet.peranner.model.User;

public interface CrudService<E, I extends Number> {
    E save(E entity, User user);

    Optional<E> findById(I id);

    List<E> findAllByUser(User user);

    E update(I id, E e);

    void deleteById(I id);
}
