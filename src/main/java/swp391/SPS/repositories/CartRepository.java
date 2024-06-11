package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Phone;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT p FROM Cart c JOIN c.phones p WHERE c.cartId = :cartId")
    List<Phone> findPhonesByCartId(@Param("cartId") int cartId);

    @Query("SELECT c FROM Cart c JOIN User u ON c.cartId = u.cart.cartId WHERE u.userId = ?1")
    Cart getCartByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_phone WHERE cart_id = :cartId AND phone_id = :phoneId", nativeQuery = true)
    void deletePhoneFromCart(@Param("cartId") int cartId, @Param("phoneId") int phoneId);
}
