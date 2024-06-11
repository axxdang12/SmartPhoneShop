package swp391.SPS.services;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Phone;

import java.util.List;

public interface CartService {
    List<Phone> getProductByCartId(int id);
    Cart getCart(int userId);
    void removePhoneFromCart(int id, int phoneId);
}
