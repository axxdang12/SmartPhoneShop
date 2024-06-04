package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.PhoneService;

@Controller
public class ShopController {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.findAllPhone());
        return "shop";
    }
}
