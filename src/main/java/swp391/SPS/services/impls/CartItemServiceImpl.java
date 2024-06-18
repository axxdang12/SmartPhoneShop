package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.CartItem;
import swp391.SPS.entities.Phone;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.CartItemRepository;
import swp391.SPS.repositories.CartRepository;
import swp391.SPS.repositories.PhoneRepository;
import swp391.SPS.repositories.UserRepository;
import swp391.SPS.services.CartItemService;

import java.util.List;


@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void removePhoneFromCart(String userName,int cartId, int phoneId) {
        double total=0;
        User user = userRepository.findByUsername(userName);
        Cart cart = user.getCart();
        List<CartItem> ciList = cart.getItems();
        cartItemRepository.deletePhoneFromCart(cartId, phoneId);
        for (CartItem item : ciList) {
            total += item.getTotal();
            //cartItemRepository.save(item);
        }
        cart.setTotal(total);
        cartRepository.save(cart);
    }

    @Override
    public void addPhoneToCart(String userName, int phoneId) {
        double total = 0;
        Phone upd = phoneRepository.findById(phoneId).orElse(null);
        if (upd == null) {
            // Xử lý trường hợp không tìm thấy điện thoại
            return;
        }

        User user = userRepository.findByUsername(userName);
        Cart cart = user.getCart();
        List<CartItem> ciList = cart.getItems();

        boolean found = false;
        for (CartItem item : ciList) {
            if (item.getPhone().getPhoneId() == upd.getPhoneId()) {
                item.setQuantity(item.getQuantity() + 1);
                //cartItemRepository.save(item);
                found = true;
                break;
            }
        }
        if (!found) {
            ciList.add(CartItem.builder().phone(upd).cart(cart).quantity(1).build());
        }
        for (CartItem item : ciList) {
            item.setTotal(item.getTotalPrice());
            total += item.getTotal();
            //cartItemRepository.save(item);
        }
        cart.setTotal(total);
        cartRepository.save(cart);
    }

    @Override
    public void updatePhoneQuantity(String userName,int cartId, int phoneId, int quantity) {
        double total=0;
        CartItem cartItem=cartItemRepository.listCartItemByPAC(cartId,phoneId);
        User user = userRepository.findByUsername(userName);
        Cart cart = user.getCart();
        List<CartItem> ciList = cart.getItems();
        if (cartItem!=null){
            cartItem.setQuantity(quantity);
            cartItem.setTotal(cartItem.getTotalPrice());
            for (CartItem item : ciList) {
                total+=item.getTotal();
            }
            cart.setTotal(total);
            cartItemRepository.save(cartItem);
            cartRepository.save(cart);
        }
    }
}
