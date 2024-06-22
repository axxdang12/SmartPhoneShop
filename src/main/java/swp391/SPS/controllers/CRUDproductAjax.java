package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.entities.Phone;
import swp391.SPS.entities.Picture;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.PhoneService;
import swp391.SPS.services.PictureService;

import java.time.LocalDate;
import java.util.*;

@RestController

public class CRUDproductAjax {
    @Autowired
    PhoneService phoneService;

    @Autowired
    BrandService brandService;

    @Autowired
    PictureService pictureService;


    @GetMapping("/api/phones")
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phone = phoneService.findAllPhone();
        return ResponseEntity.ok(phone);
    }





    @PostMapping("/api/edit-product")
    public ResponseEntity<Map<String, Object>> getPhoneById( @RequestBody String request) {


        Phone phone = phoneService.getPhoneByID((Integer.parseInt(request)));
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

        @PostMapping("/api/change-status")
        public ResponseEntity<Map<String, Object>> deletePhone(@RequestBody Map<String, Integer> request) {
            int id = request.get("id");

            Map<String, Object> response = new HashMap<>();
            Phone phone = phoneService.getPhoneByID(id);
            if (phone != null) {
                phoneService.changeStatus(phone);
                response.put("status", "success");
                response.put("message", "Phone deleted successfully.");
            } else {
                response.put("status", "error");
                response.put("message", "Phone not found.");
            }
            return ResponseEntity.ok(response);
        }


}
