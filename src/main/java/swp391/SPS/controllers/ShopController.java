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
import swp391.SPS.services.AccessService;
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
    @Autowired
    AccessService accessService;

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listCategory", categoryService.findAllCategory());
        model.addAttribute("listPhone", phoneService.findAllPhone());
        model.addAttribute("listA", accessService.findAllAccess());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                model.addAttribute("isLogin", false);
                return "shop";
            }
            model.addAttribute("isLogin", true);
            model.addAttribute("username", authentication.getName());

        return "shop";

        }
    @GetMapping("/shop/brand/{idBrand}")
    public String ProductByBrand(@PathVariable("idBrand") int id, Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.getPhoneByBrand(id));
        model.addAttribute("listA", accessService.getAccessByBrand(id));
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

    @GetMapping("/shop/category/{idCategory}")
    public String ProductByCategory(@PathVariable("idCategory") int id, Model model){
        model.addAttribute("listCategory", categoryService.findAllCategory());
        model.addAttribute("listPhone", phoneService.getPhoneByCategory(id));
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
