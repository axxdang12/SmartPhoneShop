package swp391.SPS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Phone;

import java.util.List;

@Repository
public interface AccessRepository extends JpaRepository<Accessory, Integer>{
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM accessory WHERE accessory_name LIKE %:name%", nativeQuery = true)
    List<Accessory> SearchAccess(@Param("name") String name);
}