package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
//import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Order;
import swp391.SPS.entities.Phone;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM ordertb WHERE ordertb.user_id = :userId" , nativeQuery = true)
    List<Order> getOrderByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "select * from order_phone where order_id= :order_id" , nativeQuery = true)
    List<Order> getOrderPhoneById(@Param("order_id") int order_id);

    @Query("SELECT p FROM Order o JOIN o.phones p WHERE o.orderId = :orderId")
    List<Phone> findPhonesByOrderId(@Param("orderId") int orderId);

//    @Query("SELECT a FROM Order o JOIN o.accessories a WHERE o.orderId = :orderId")
//    List<Accessory> findAccessoryByOrderId(@Param("orderId") int orderId);

}
