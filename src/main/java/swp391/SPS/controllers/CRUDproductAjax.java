package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.PhoneService;
import swp391.SPS.services.PictureService;

import java.util.HashMap;
import java.util.Map;
@RestController

public class CRUDproductAjax {
    @Autowired
    PhoneService phoneService;

    @Autowired
    BrandService brandService;

    @Autowired
    PictureService pictureService;

    @PostMapping("/api/add-product")
    public ResponseEntity<Map<String, Object>> addPhone( @RequestBody Phone phone) {
        Phone p = Phone.builder().productName(phone.getProductName()).phoneId(phone.getPhoneId())
                .cpu(phone.getCpu()).ram(phone.getRam()).sim(phone.getSim()).price(phone.getPrice()).
                camera(phone.getCamera()).memory(phone.getMemory()).origin(phone.getOrigin()).
                brand(phone.getBrand()).picture(phone.getPicture()).releaseDate(phone.getReleaseDate())
                .display(phone.getDisplay()).build();

        if (p != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("listBrand", brandService.findAllBrand());
            response.put("phone", phone);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/edit-product")
    public ResponseEntity<Map<String, Object>> getPhoneById( @RequestBody Map<String, Integer> request) {

        int phoneId = request.get("id");
        Phone phone = phoneService.getPhoneByID(phoneId);
        if (phone != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("listBrand", brandService.findAllBrand());
            response.put("phone", phone);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/api/update-product")
    public ResponseEntity<String> updatePhone(@RequestBody Phone phone, Model model){
        model.addAttribute("listBrand", brandService.findAllBrand());
        phoneService.editPhone(phone);
        return ResponseEntity.ok("Product has id is "+ phone.getPhoneId() +"updated sucessfully");
    }

    @PostMapping("/api/delete-product")
    public ResponseEntity<String> deletePhone( @RequestBody Map<String, Integer> request){
        int phoneid = request.get("id");
        Phone phone = phoneService.getPhoneByID(phoneid);
        pictureService.deletePicture(phone.getPicture());
        phoneService.deletephone(phone);
        return ResponseEntity.ok("Delete successfully");
    }


}
