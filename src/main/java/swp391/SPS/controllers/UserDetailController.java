package swp391.SPS.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp391.SPS.services.UserDetailService;
import swp391.SPS.services.UserService;

@Controller
@AllArgsConstructor
public class UserDetailController {
    private UserService userService;
    private UserDetailService userDetailService;
    @GetMapping("/user_detail/{id}")
    public String user_detail(Model model, @PathVariable(name = "id") int userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "user_detail";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userdtl", userDetailService.getUserDetailByUserId(userId));
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        return "user_detail";
    }
}
