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
import java.util.List;
import java.util.Map;


@Controller
public class ManagerProduct {

    @Autowired
    BrandService brandService;
    @Autowired
    PhoneService phoneService;

    @Autowired
    PictureService pictureService;



    @GetMapping("/manageProduct")
    public String viewProduct(Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());

        model.addAttribute("listPhone", phoneService.findAllPhone());

        return"products";
    }
    @GetMapping("/add-product")
    public String addP(Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
//        model.addAttribute("listCategory", categoryService.findAllCategory());

        return "add-product";
    }
    @PostMapping("/edit-product")
    public String viewEditphone(@RequestParam("id") int id, Model model){
        model.addAttribute("phone",phoneService.getPhoneByID(id));
        return "Edit-product";
    }
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
                             @RequestParam("status") Boolean status,
                             @RequestParam("date") Date date,Model model,RedirectAttributes redirectAttributes){
//        model.addAttribute("listCategory", categoryService.findAllCategory());
        model.addAttribute("listBrand", brandService.findAllBrand());
        Picture picture = new Picture(picid,pm,pf,pb,ps);
        pictureService.editPircure(pictureService.getPictureById(picid));
//        Category c = categoryService.getCategory(cate);
        Brand b = brandService.getBrand(brand);

        Phone phone = new Phone();
        phone = Phone.builder().productName(productName).status(status).phoneId(pid).cpu(cpu).ram(ram).sim(sim).price(price).camera(camera).memory(memory).origin(origin).brand(b).picture(picture).releaseDate(date.toLocalDate()).display(dis).build();
        phoneService.editPhone(phone);
       redirectAttributes.addFlashAttribute("message", "Chỉnh sửa sản phẩm thành công.");
       return "redirect:/manageProduct";
    }


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
                             @RequestParam("status") Boolean status,
                             Model model){

        Picture picture = new Picture();
        picture = Picture.builder().site(ps).back(pb).front(pf).main(pm).build();
        pictureService.addPicture(picture);
        Brand b = brandService.getBrand(brand);
        picture = pictureService.getPictureById(picture.getPictureId());
        Phone phone = new Phone();
        phone = Phone.builder().productName(productName).status(status).cpu(cpu).ram(ram).sim(sim).price(price).camera(camera).memory(memory).origin(origin).brand(b).picture(picture).releaseDate(date.toLocalDate()).display(dis).build();
        phoneService.addPhone(phone);
        List<Phone> lphone = phoneService.findAllPhone();
        for(Phone p : lphone){
            if(p.equals(phone)) {
                model.addAttribute("listBrand", brandService.findAllBrand());
                model.addAttribute("mess", "Thêm sản phẩm thành công");
                return"add-product";
            }
        }
        model.addAttribute("listBrand", brandService.findAllBrand());
        model.addAttribute("mess", "Thêm sản phẩm không thành công");
        return"add-product";
    }

}
