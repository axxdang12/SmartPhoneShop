package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import swp391.SPS.services.AccessService;
import swp391.SPS.services.PhoneService;

@Controller
public class SingleProductController {
    @Autowired
    PhoneService phoneService;

    @GetMapping("/single-product/{id}")
    public String SingleProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product",phoneService.getPhoneByID(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "single-product";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        return "single-product";
    }
//    @Autowired
//    AccessService accessService;
//
//    @GetMapping("/single-access/{id}")
//    public String accessory(@PathVariable("id") int id, Model model){
//        model.addAttribute("product",accessService.getAccessByID(id));
//        return "single-access";
//    }

}
