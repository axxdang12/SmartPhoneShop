package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.SPS.services.OrderService;

@Controller
public class ManagerController {


    @Autowired
    private OrderService orderService;

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
}
