package swp391.SPS.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391.SPS.entities.User;

@Controller
@CrossOrigin
public class MainController {
  @RequestMapping("/page/login")
  @CrossOrigin
  public String login() {
    return "login";
  }

  @RequestMapping("/index")
  @CrossOrigin
  public String index(Model model) {
    model.addAttribute("title", "Home Page");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      return "redirect:/login";
    }
    return "index";
  }

  //  @PostMapping("/do-login")
  //  public String loginPost() {
  //    return "redirect:index";
  //  }
}
