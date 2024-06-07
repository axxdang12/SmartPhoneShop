package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp391.SPS.services.PhoneService;

@Controller
public class SingleProductController {
    @Autowired
    PhoneService phoneService;

    @GetMapping("/single-product/{id}")
    public String SingleProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product",phoneService.getPhoneByID(id));
        return "single-product";
    }
}
