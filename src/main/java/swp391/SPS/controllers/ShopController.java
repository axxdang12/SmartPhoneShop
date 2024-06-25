package swp391.SPS.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import swp391.SPS.services.AccessService;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.BrandService;
//import swp391.SPS.services.CategoryService;
import swp391.SPS.services.PhoneService;

@Controller
public class ShopController {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;

    @GetMapping("/shop")
    public String shop(Model model,@RequestParam(name = "keyword", required = false) String name, @RequestParam(name = "pageNo", defaultValue = "1") int page) {
        model.addAttribute("listBrand", brandService.findAllBrand());
            Page<Phone> list = phoneService.phoneforshop(page);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                model.addAttribute("isLogin", false);
                return "shop";
            }
            if(name!=null && !name.isEmpty()){
                list = phoneService.searchPhoneforShop(name,page);
                model.addAttribute("keyword", name);
            }
            model.addAttribute("listPhone", list);
            model.addAttribute("totalPage", list.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("isLogin", true);
            model.addAttribute("username", authentication.getName());

        return "shop";

        }

//    @GetMapping("/search")
//    public String search(@RequestParam("name") String name, Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            model.addAttribute("isLogin", false);
//            return "shop";
//        }
//        model.addAttribute("isLogin", true);
//        model.addAttribute("username", authentication.getName());
//        model.addAttribute("listPhone", phoneService.searchPhone(name));
//        model.addAttribute("listBrand", brandService.findAllBrand());
//        return"shop";
//    }

    @GetMapping("/shop/brand/{idBrand}")
    public String ProductByBrand(@PathVariable("idBrand") int id, Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.getPhoneByBrand(id));
//        model.addAttribute("listA", accessService.getAccessByBrand(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLogin", false);
            return "shop";
        }
        model.addAttribute("isLogin", true);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("listBrand", brandService.findAllBrand());
        return "shop";
    }

//    }


}
