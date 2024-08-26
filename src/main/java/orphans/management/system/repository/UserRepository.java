package orphans.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orphans.management.system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByUserId(long userId);

    @Query("SELECT COUNT(*) FROM User u")
    Integer getTotalUsers();

    @Query("SELECT COUNT(*) FROM User u WHERE u.role = 'ROLE_DONOR'")
    Integer getTotalDonors();
}
