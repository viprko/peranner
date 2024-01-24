package pet.peranner.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.DevoteTime;
import pet.peranner.model.User;

@Repository
public interface DevoteTimeRepository extends JpaRepository<DevoteTime, Long> {
    @Query("SELECT dt FROM DevoteTime dt WHERE dt.user = :user")
    public List<DevoteTime> findAllByUser(@Param("user") User user);

    @Query("SELECT dt FROM DevoteTime dt WHERE dt.user = :user "
            + "AND dt.startTime >= :periodStart "
            + "AND dt.finishTime < :periodEnd")
    public List<DevoteTime> findByPeriod(@Param("periodStart") LocalDateTime periodStart,
                                         @Param("periodEnd") LocalDateTime periodEnd,
                                         @Param("user") User currentUser);
}
