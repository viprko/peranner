package pet.peranner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.peranner.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
