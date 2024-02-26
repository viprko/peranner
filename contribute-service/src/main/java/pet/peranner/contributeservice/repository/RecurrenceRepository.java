package pet.peranner.contributeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.peranner.contributeservice.model.Recurrence;

@Repository
public interface RecurrenceRepository extends JpaRepository<Recurrence, Long> {
}
