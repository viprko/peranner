package pet.peranner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.model.Task;
import pet.peranner.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.title LIKE %:substring%")
    public List<Task> findByTitle(@Param("user") User user,
                                  @Param("substring") String titleSubstring);

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.description LIKE %:substring%")
    public List<Task> findByDescription(@Param("user") User user,
                                        @Param("substring") String descriptionSubstring);

    @Query("SELECT t FROM Task t WHERE t.user = :email")
    public List<Task> findAllByUserEmail(@Param("email")String email);
}
