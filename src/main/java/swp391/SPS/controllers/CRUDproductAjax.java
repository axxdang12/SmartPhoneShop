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


    @GetMapping("/api/products")
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phone = phoneService.findAllPhone();
        return ResponseEntity.ok(phone);
    }

    @PostMapping("/api/add-product")
    public ResponseEntity<Map<String, Object>> addPhone(@RequestBody Map<String,Object> request) {
        if (request == null || !request.containsKey("productData") || !request.containsKey("picture")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid request data"));
        }

        Map<String, Object> phoneData = (Map<String, Object>) request.get("productData");
        Map<String, Object> pictureData = (Map<String, Object>) request.get("picture");

        try {
            Picture picture = Picture.builder()
                    .main((String) pictureData.get("pm"))
                    .front((String) pictureData.get("pf"))
                    .back((String) pictureData.get("pb"))
                    .site((String) pictureData.get("ps"))
                    .build();

            Phone phone = Phone.builder()
                    .productName((String) phoneData.get("productName"))
                    .cpu((String) phoneData.get("cpu"))
                    .ram((Integer) phoneData.get("ram"))
                    .sim((String) phoneData.get("sim"))
                    .price((Double) phoneData.get("price"))
                    .camera((Double) phoneData.get("camera"))
                    .memory((Double) phoneData.get("memory"))
                    .origin((String) phoneData.get("origin"))
                    .brand(brandService.getBrand((Integer) phoneData.get("brandId")))
                    .picture(picture)
                    .releaseDate((LocalDate) phoneData.get("releaseDate"))
                    .display((Double) phoneData.get("dis"))
                    .build();

            // Kiểm tra nếu dữ liệu của phone là hợp lệ và đã tạo thành công phone
            if (phone != null) {
                // Lưu phone vào cơ sở dữ liệu hoặc thực hiện các thao tác khác ở đây
                // Trả về phản hồi thành công
                return ResponseEntity.ok().body(Collections.singletonMap("message", "Phone added successfully"));
            } else {
                // Trả về phản hồi lỗi nếu không tạo được phone
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to add phone"));
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về phản hồi lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Internal server error"));
        }
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

    @PostMapping("/api/delete-product")
    public ResponseEntity<Map<String, Object>> deletePhone( @RequestBody Map<String, Integer> request){
        int phoneid = request.get("id");
        Map<String, Object> response = new HashMap<>();
        Phone phone = phoneService.getPhoneByID(phoneid);
        if (phone != null) {
            pictureService.deletePicture(phone.getPicture());
            phoneService.deletephone(phone);
            response.put("status", "success");
            response.put("message", "Phone deleted successfully.");
        } else {
            response.put("status", "error");
            response.put("message", "Phone not found.");
        }
        return ResponseEntity.ok(response);
    }


}
