package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.SPS.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
