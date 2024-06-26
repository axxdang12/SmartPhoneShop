package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swp391.SPS.services.OrderItemService;
import swp391.SPS.services.OrderService;
import swp391.SPS.services.UserService;

@Controller
public class ManagerController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserService userService;

    @GetMapping("/manager")
    public String viewOrderList(Model model){
        model.addAttribute("orderList", orderService.getAllOrder());
        return "manager";
    }

    @PostMapping("/searchorder")
    public String searchOrderById(@RequestParam("userid") int id, Model model){
        model.addAttribute("listOrderByUser", orderService.searchOrderByUserId(id));
        return "manager";
    }
    @GetMapping("/order-detail-manager/{id}")
    public String detailOrder(@PathVariable("id") int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "order-detail";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listItemByO", orderItemService.listOrderItemByOrderId(id));
        model.addAttribute("orderByOrderId",orderService.getOrder(id));
        model.addAttribute("userByOrderId", userService.findUserByOrderId(id));
            return "order-detail";
    }

    @GetMapping("/approve/{id}")
    public String approveOrder(@PathVariable int id, RedirectAttributes redirectAttributes) {
        orderService.updateOrderStatus(id, "Approved");
        redirectAttributes.addFlashAttribute("message", "Order approved successfully");
        return "redirect:/manager";
    }

    @GetMapping("/reject/{id}")
    public String rejectOrder(@PathVariable int id, RedirectAttributes redirectAttributes) {
        orderService.updateOrderStatus(id, "Rejected");
        redirectAttributes.addFlashAttribute("message", "Order rejected successfully");
        return "redirect:/manager";
    }

    @GetMapping("/complete/{id}")
    public String completeOrder(@PathVariable int id, RedirectAttributes redirectAttributes) {
        orderService.updateOrderStatus(id, "Completed");
        redirectAttributes.addFlashAttribute("message", "Order completed successfully");
        return "redirect:/manager";
    }
}
