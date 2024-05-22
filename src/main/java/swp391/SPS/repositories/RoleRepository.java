package swp391.SPS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.SPS.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM role r JOIN r.user u WHERE u.userId = ?1")
    Role findRole(int userId);

}
