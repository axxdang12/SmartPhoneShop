package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swp391.SPS.entities.Brand;
//import swp391.SPS.entities.Category;
import swp391.SPS.entities.Phone;
import swp391.SPS.entities.Picture;
import swp391.SPS.services.*;

import java.sql.Date;
import java.util.Map;


@Controller
public class ManagerProduct {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;
//    @Autowired
//    CategoryService categoryService;
//    @Autowired
//    AccessService accessService;
    @Autowired
    PictureService pictureService;



    @GetMapping("/manageProduct")
    public String viewProduct(Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());

        model.addAttribute("listPhone", phoneService.findAllPhone());

        return"manageProduct";
    }
    @GetMapping("/add-product")
    public String addP(Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
//        model.addAttribute("listCategory", categoryService.findAllCategory());

        return "add-product";
    }

//    @GetMapping("/add-access")
//    public String addA(){
//        return "add-access";
//    }

//    @GetMapping("/edit-product/{id}")
//    public String viewEdit(@PathVariable("id") int id,Model model){
////        model.addAttribute("listCategory", categoryService.findAllCategory());
//        model.addAttribute("listBrand", brandService.findAllBrand());
//        model.addAttribute("phone", phoneService.getPhoneByID(id) );
//        return "Edit-product";
//    }


//    @PostMapping("/edit-product")
//    @ResponseBody
//    public Phone getProductDetails(@RequestBody Map<String, Integer> request) {
//        int phoneId = request.get("id");
//        model.addAttribute("listBrand", brandService.findAllBrand());
//        return phoneService.getPhoneByID(phoneId);
//    }

    @PostMapping("/editProduct")
    public String EditPhone( @RequestParam("pid") int pid,
                             @RequestParam("picid") int picid,
                             @RequestParam("name") String productName,
                             @RequestParam("price") double price,
                             @RequestParam("cpu") String cpu,
                             @RequestParam("memory") double memory,
//                             @RequestParam("category") int cate,
                             @RequestParam("sim") String sim,
                             @RequestParam("ram") int ram,
                             @RequestParam("dis") double dis,
                             @RequestParam("origin") String origin,
                             @RequestParam("brand") int brand,
                             @RequestParam("pm") String pm,
                             @RequestParam("pf") String pf,
                             @RequestParam("pb") String pb,
                             @RequestParam("ps") String ps,
                             @RequestParam("camera") double camera,
                             @RequestParam("date") Date date,Model model){
//        model.addAttribute("listCategory", categoryService.findAllCategory());
        model.addAttribute("listBrand", brandService.findAllBrand());
        Picture picture = new Picture(picid,pm,pf,pb,ps);
        pictureService.editPircure(pictureService.getPictureById(picid));
//        Category c = categoryService.getCategory(cate);
        Brand b = brandService.getBrand(brand);

        Phone phone = new Phone();
        phone = Phone.builder().productName(productName).phoneId(pid).cpu(cpu).ram(ram).sim(sim).price(price).camera(camera).memory(memory).origin(origin).brand(b).picture(picture).releaseDate(date.toLocalDate()).display(dis).build();
        phoneService.editPhone(phone);
        RedirectAttributes redirectAttributes;
        return "redirect:/products";
    }


//    @GetMapping("/edit-access")
//    public String viewEditA(){
//        return "edit-access";
//    }


    @GetMapping("/add-brand")
    public String addBrand(){
        return "add-brand";
    }


    @GetMapping("/edit-brand")
    public String editBrand(){
        return "edit-brand";
    }
    @PostMapping("/addProduct")
    public String addProduct(
                             @RequestParam("name") String productName,
                             @RequestParam("price") int price,
                             @RequestParam("cpu") String cpu,
                             @RequestParam("memory") int memory,
//                             @RequestParam("category") int cate,
                             @RequestParam("sim") String sim,
                             @RequestParam("ram") int ram,
                             @RequestParam("dis") int dis,
                             @RequestParam("origin") String origin,
                             @RequestParam("brand") int brand,
                             @RequestParam("pm") String pm,
                             @RequestParam("pf") String pf,
                             @RequestParam("pb") String pb,
                             @RequestParam("ps") String ps,
                             @RequestParam("camera") int camera,
                             @RequestParam("date") Date date,
                             Model model){

        Picture picture = new Picture();
        picture = picture.createPicture(pm,pf,pb,ps);
        pictureService.addPicture(picture);
//        Category c = categoryService.getCategory(cate);
        Brand b = brandService.getBrand(brand);
        picture = pictureService.getPictureById(picture.getPictureId());
        Phone phone = new Phone();
        phone = phone.createPhone(productName,(double)price,cpu,ram,(double)memory,(double)dis,(double)camera,origin,sim, date.toLocalDate(),b,picture);
        phoneService.addPhone(phone);
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("listPhone", phoneService.findAllPhone());
        return"manageProduct";
    }


    @GetMapping("/deletephone/{id}")
    public String deletephone(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        Phone phone = phoneService.getPhoneByID(id);
        phoneService.deletephone(phone);
        pictureService.deletePicture(phone.getPicture());
        redirectAttributes.addFlashAttribute("deleteSuccess", true);
        return "redirect:/products";
    }

}
