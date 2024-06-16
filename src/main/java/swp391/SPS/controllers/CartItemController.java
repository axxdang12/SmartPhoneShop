package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp391.SPS.entities.CartItem;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.CartItemService;
import swp391.SPS.services.PhoneService;


@Controller
public class CartItemController {

    @Autowired
    PhoneService phoneService;
    @Autowired
    CartItemService cartItemService;

    @GetMapping("/cart")
    public String shop(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "cart";
        }
        model.addAttribute("cartItem",cartItemService.getAllItems());
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "cart";
        }
        Phone phone=phoneService.getPhoneByID(id);
        if (phone!=null){
            CartItem cartItem=new CartItem();
            cartItem.setProductId(phone.getPhoneId());
            cartItem.setPicture(phone.getPicture().getMain());
            cartItem.setName(phone.getProductName());
            cartItem.setPrice(phone.getPrice());
            cartItem.setQuantity(1);
            cartItemService.add(cartItem);
        }
        model.addAttribute("cartItem",cartItemService.getAllItems());
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        return "cart";
    }

}
