package swp391.SPS.services;
import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Phone;

import java.util.List;

public interface CartService {
    List<Phone> getProductByCartId(int id);
    List<Accessory> getAccessoryByCartId(int id);
    Cart getCart(int userId);
    void removePhoneFromCart(int id, int phoneId);
    void removeAccessoryFromCart(int id, int aId);
    void addPhoneFromCart(int id, int phoneId);
    void addAccessoryFromCart(int id, int aId);
}
