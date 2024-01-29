package pet.peranner.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.Contribute;
import pet.peranner.model.User;

@Repository
public interface ContributeRepository extends JpaRepository<Contribute, Long> {
    @Query("SELECT c FROM Contribute c WHERE c.user = :user")
    public List<Contribute> findAllByUser(@Param("user") User user);

    @Query("SELECT c FROM Contribute c WHERE c.user = :user "
            + "AND c.startTime >= :periodStart "
            + "AND c.finishTime < :periodEnd")
    public List<Contribute> findByPeriod(@Param("periodStart") LocalDateTime periodStart,
                                         @Param("periodEnd") LocalDateTime periodEnd,
                                         @Param("user") User currentUser);
}
