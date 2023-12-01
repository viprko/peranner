package pet.peranner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE p.user = :email")
    public List<Place> findAllByUserEmail(@Param("email") String email);

    @Query("SELECT p FROM Place p WHERE p.title LIKE %:substring%")
    public List<Place> findByTitle(@Param("substring") String titleSubstring);
}
