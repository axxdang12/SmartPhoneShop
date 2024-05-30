package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.SPS.services.BrandService;

@Controller
public class ShopController {

    @Autowired
    BrandService brandService;

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("listBrand", brandService.findAllBrand());
        return "shop";
    }
}
