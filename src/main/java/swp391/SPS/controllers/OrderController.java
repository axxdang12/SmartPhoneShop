package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp391.SPS.entities.Order;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.OrderService;
import swp391.SPS.services.PhoneService;
import swp391.SPS.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    PhoneService phoneService;


    @GetMapping("/userorder")
    public String userOrder(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "userorder";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("orderListByUid",orderService.ListOrderByUserId(userService.getUserId(authentication.getName())));
        return "userorder";
    }

    @GetMapping("/detail/{id}")
    public String detailOrder(@PathVariable("id") int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "detail";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listPByO", orderService.getProductByOrderId(id));
//        model.addAttribute("listAByO", orderService.getAccessoryByOrderId(id));
        return "detail";
    }
}
