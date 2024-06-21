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
import swp391.SPS.entities.OrderItem;
import swp391.SPS.services.CartService;
import swp391.SPS.services.OrderItemService;
import swp391.SPS.services.OrderService;

import java.util.List;


@Controller
public class ReportController {

    @Autowired
    OrderItemService orderItemService;

//    @GetMapping("/report")
//    public String detail(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            model.addAttribute("isLogin", false);
//            return "report";
//        }
//        model.addAttribute("isLogin", true);
//        model.addAttribute("username", authentication.getName());
//        return "report";
//    }

    @GetMapping("/report/{id}")
    public String showReportForm(@PathVariable("id") int orderId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "report";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listItemByO", orderItemService.listOrderItemByOrderId(orderId));
        return "report";
    }
}
