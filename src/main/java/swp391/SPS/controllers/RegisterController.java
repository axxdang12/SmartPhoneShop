package swp391.SPS.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.UserRepository;
import swp391.SPS.services.UserService;

@Controller
public class RegisterController {
  @Autowired private UserService userService;
  @Autowired private BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("title", "Register");
    model.addAttribute("userDto", new UserDto());
    return "register";
  }

  @PostMapping("/register-new")
  public String addNewAdmin(
      @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {

    try {

      if (result.hasErrors()) {
        model.addAttribute("userDto", userDto);
        return "register";
      }
      String username = userDto.getUsername();
      User user = userService.findByUsername(username);
      if (user != null) {
        model.addAttribute("userDto", userDto);
        System.out.println("user not null");
        model.addAttribute("usernameError", "Your username has been registered!");
        return "register";
      }
      if (userDto.getPassword().equals(userDto.getRepeatPassword())) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.save(userDto);
        System.out.println("success");
        model.addAttribute("success", "Register successfully!");
        model.addAttribute("userDto", userDto);
      } else {
        model.addAttribute("userDto", userDto);
        model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
        System.out.println("password not same");
      }
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("errors", "The server has been wrong!");
    }
    return "register";
  }
}
