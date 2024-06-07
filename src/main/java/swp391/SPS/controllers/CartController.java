package swp391.SPS.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp391.SPS.entities.Cart;
import swp391.SPS.services.CartService;
import swp391.SPS.services.UserService;


@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("/cart")
    public String shop(Model model) {


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                model.addAttribute("isLogin", false);
                return "cart";
            }
            model.addAttribute("isLogin", true);
            model.addAttribute("username", authentication.getName());
            Cart cart= cartService.getCart(userService.getUserId(authentication.getName()));
            model.addAttribute("listPByC", cartService.getProductByCartId(cart.getCartId()));
        return "cart";
        }

    @GetMapping("/cart/{id}")
    public String deletePhone(@PathVariable("id") int id,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "cart";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        Cart cart= cartService.getCart(userService.getUserId(authentication.getName()));
        cartService.removePhoneFromCart(cart.getCartId(),id);
        model.addAttribute("listPByC", cartService.getProductByCartId(cart.getCartId()));
        return "cart";
    }
}
