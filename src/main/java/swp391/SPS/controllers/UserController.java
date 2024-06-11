package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.dtos.ProfileDto;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.User;
import swp391.SPS.services.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "profile";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        return "profile";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "checkout";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        return "checkout";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Model model, @ModelAttribute("profileDto")ProfileDto profileDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "profile";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        userService.saveProfile(profileDto,authentication.getName());
        return "profile";
    }
}
