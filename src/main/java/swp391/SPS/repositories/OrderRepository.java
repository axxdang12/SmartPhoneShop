package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.SPS.entities.Order;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM ordertb WHERE ordertb.user_id = :userId" , nativeQuery = true)
    List<Order> getOrderByUserId(@Param("userId") int userId);

}
