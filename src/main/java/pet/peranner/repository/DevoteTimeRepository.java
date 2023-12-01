package pet.peranner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.DevoteTime;

@Repository
public interface DevoteTimeRepository extends JpaRepository<DevoteTime, Long> {

    @Query("SELECT dt FROM DevoteTime dt WHERE dt.user = :email")
    public List<DevoteTime> findAllByUserEmail(@Param("email") String email);
}
