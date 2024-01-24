package pet.peranner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.Place;
import pet.peranner.model.User;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE p.user = :user")
    public List<Place> findAllByUser(@Param("user") User user);

    @Query("SELECT p FROM Place p WHERE p.title LIKE %:substring%")
    public List<Place> findByTitle(@Param("substring") String titleSubstring);
}
