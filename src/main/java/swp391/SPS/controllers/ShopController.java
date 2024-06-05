package swp391.SPS.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.CategoryService;
import swp391.SPS.services.PhoneService;

@Controller
public class ShopController {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.findAllPhone());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                model.addAttribute("isLogin", false);
                return "shop";
            }
            model.addAttribute("isLogin", true);
            model.addAttribute("username", authentication.getName());
            model.addAttribute("listBrand", brandService.findAllBrand());
            model.addAttribute("listCategory", categoryService.findAllCategory());
        return "shop";

        }
    @GetMapping("/shop/{idBrand}")
    public String ProductByBrand(@PathVariable("idBrand") int id, Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.getPhoneByBrand(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "shop";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listCategory", categoryService.findAllCategory());
        return "shop";
    }

}
