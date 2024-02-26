package pet.peranner.contributeservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.contributeservice.model.Contribute;

@Repository
public interface ContributeRepository extends JpaRepository<Contribute, Long> {
    @Query("SELECT c FROM Contribute c WHERE c.userId = :userId")
    List<Contribute> findAllByUserId(@Param("user") Long userId);

    @Query("SELECT c FROM Contribute c WHERE c.userId = :userId "
            + "AND c.startTime >= :periodStart "
            + "AND c.finishTime < :periodEnd")
    List<Contribute> findByPeriod(@Param("periodStart") LocalDateTime periodStart,
                                  @Param("periodEnd") LocalDateTime periodEnd,
                                  @Param("userId") Long userId);
}
