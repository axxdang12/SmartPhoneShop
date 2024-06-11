package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.SPS.entities.Phone;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM phone WHERE product_name LIKE %:name%", nativeQuery = true)
    List<Phone> SearchProduct(@Param("name") String name);





}
