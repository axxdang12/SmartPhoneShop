package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.SPS.entities.Account;
import swp391.SPS.services.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String hello(Model model) {
        String name = accountService.getUserName(1);
        model.addAttribute("message", name);
        return "index";
    }
}
