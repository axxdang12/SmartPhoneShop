package swp391.SPS.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
public class MainController {
  @GetMapping("/page/login")
  @CrossOrigin
  public String login() {
    return "login";
  }

  @GetMapping("/home-page")
  @CrossOrigin
  public String index(Model model) {
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    //      return "redirect:/login";
    //    }
    //    model.addAttribute("username", authentication.getPrincipal().toString());
    return "index";
  }

  @RequestMapping(value = "/admin-dashboard", method = RequestMethod.GET)
  public String adminDashBoard(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      return "redirect:/login";
    }
    model.addAttribute("username", authentication.getName());
    return "admin-dashboard";
  }

  @RequestMapping(value = "/manager-dashboard", method = RequestMethod.GET)
  public String managerDashBoard() {
    return "manager-dashboard";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @GetMapping("/cart")
  public String cart() {
    return "cart";
  }

  @GetMapping("/checkout")
  public String checkout() {
    return "checkout";
  }

  @GetMapping("/single-product")
  public String singleProduct() {
    return "register";
  }

  @GetMapping("/shop")
  public String shop() {
    return "shop";
  }

  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }

  @GetMapping("/userorder")
  public String userOrder() {

    return "userorder";
  }

  @GetMapping("/detail")
  public String detail() {

    return "detail";
  }
}
