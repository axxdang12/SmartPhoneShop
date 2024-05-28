package swp391.SPS.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class MainController {
  @RequestMapping("/page/login")
  @CrossOrigin
  public String login() {
    return "login";
  }

  @RequestMapping("/home-page")
  @CrossOrigin
  public String index(Model model) {
    model.addAttribute("title", "Home Page");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      return "redirect:/login";
    }
    return "index";
  }

  @RequestMapping("/admin-dashboard")
  public String adminDashBoard() {
    return "admin-dashboard";
  }

  @RequestMapping("/manager-dashboard")
  public String managerDashBoard() {
    return "manager-dashboard";
  }

  //  @PostMapping("/do-login")
  //  public String loginPost() {
  //    return "redirect:index";
  //  }
}
