package swp391.SPS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391.SPS.entities.User;

@Controller
public class MainController {
  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/loginpost")
  public String loginPost(@ModelAttribute User user) {
    return "redirect:index";
  }
}
