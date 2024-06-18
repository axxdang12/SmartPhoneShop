package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.BrandService;
import swp391.SPS.services.PhoneService;

import java.util.HashMap;
import java.util.Map;
@RestController

public class CRUDproductAjax {
    @Autowired
    PhoneService phoneService;

    @Autowired
    BrandService brandService;

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


}
