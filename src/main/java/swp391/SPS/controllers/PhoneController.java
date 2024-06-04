package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swp391.SPS.entities.Phone;
import swp391.SPS.services.PhoneService;

import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @PostMapping("add-phone")
    public ResponseEntity<String> addPhone (@RequestBody Phone phone){
        phoneService.addPhone(phone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
