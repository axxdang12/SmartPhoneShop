package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.SPS.services.AccessService;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.CategoryService;
import swp391.SPS.services.PhoneService;

@Controller
public class ManagerProduct {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AccessService accessService;

    @GetMapping("/products")
    public String viewProduct(Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listCategory", categoryService.findAllCategory());
        model.addAttribute("listPhone", phoneService.findAllPhone());
        model.addAttribute("listA", accessService.findAllAccess());
        return"products";
    }
    @GetMapping("/add-product")
    public String addP(){
        return "add-product";
    }

    @GetMapping("/add-access")
    public String addA(){
        return "add-access";
    }

    @GetMapping("/edit-product")
    public String viewEdit(){
        return "Edit-product";
    }


    @GetMapping("/edit-access")
    public String viewEditA(){
        return "edit-access";
    }


    @GetMapping("/add-brand")
    public String addBrand(){
        return "add-brand";
    }


    @GetMapping("/edit-brand")
    public String editBrand(){
        return "edit-brand";
    }

}
