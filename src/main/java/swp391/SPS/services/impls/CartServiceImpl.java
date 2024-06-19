package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.CartItem;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.CartItemRepository;
import swp391.SPS.repositories.CartRepository;
import swp391.SPS.services.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

//    @Override
//    public void clearCart(Cart cart) {
//        cartItemRepository.deleteAll(cart.getItems());
//        cartRepository.save(cart);
//    }

//    @Override
//    public List<Phone> getProductByCartId(int id) {
//        return cartRepository.findPhonesByCartId(id);
//    }
//
//    @Override
//    public void removePhoneFromCart(int cartId, int phoneId) {
//        cartRepository.deletePhoneFromCart(cartId, phoneId);
//    }
//
//
//    @Override
//    public void addPhoneFromCart(int id, int phoneId) {
//        cartRepository.InsertPhoneFromCart(id,phoneId);
//    }

}
