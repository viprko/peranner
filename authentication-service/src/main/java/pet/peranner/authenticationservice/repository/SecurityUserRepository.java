package pet.peranner.authenticationservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.peranner.authenticationservice.model.SecurityUser;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    @Query("SELECT su FROM SecurityUser su WHERE su.email = :email")
    Optional<SecurityUser> findByEmail(@Param("email") String email);

    @Query("UPDATE SecurityUser su SET su.password = :newPassword "
            + "WHERE su.id = :userId AND su.password = :currentPassword")
    @Modifying
    int updatePassword(@Param("userId") Long userId,
                       @Param("currentPassword") String currentPassword,
                       @Param("newPassword") String newPassword);
}
