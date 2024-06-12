package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.SPS.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUsername(String userName);
  @Query("SELECT u FROM User u WHERE u.email = ?1")
  public User findByEmail(String email);

  public User findByResetPasswordToken(String token);
}
