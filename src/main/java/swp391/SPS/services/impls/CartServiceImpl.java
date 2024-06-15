package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.CartRepository;
import swp391.SPS.services.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    public List<Phone> getProductByCartId(int id) {
        return cartRepository.findPhonesByCartId(id);
    }

//    @Override
//    public List<Accessory> getAccessoryByCartId(int id) {
//        return  cartRepository.findAccessoryByCartId(id);
//    }

    @Override
    public Cart getCart(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public void removePhoneFromCart(int cartId, int phoneId) {
        cartRepository.deletePhoneFromCart(cartId, phoneId);
    }

//    @Override
//    public void removeAccessoryFromCart(int id, int aId) {
//    cartRepository.deleteAccessoryFromCart(id, aId);
//    }

    @Override
    public void addPhoneFromCart(int id, int phoneId) {
        cartRepository.InsertPhoneFromCart(id,phoneId);
    }

//    @Override
//    public void addAccessoryFromCart(int id, int aId) {
//        cartRepository.InsertAccessoryFromCart(id,aId);
//    }


}
