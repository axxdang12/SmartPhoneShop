package swp391.SPS.services;

import org.springframework.stereotype.Service;
import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Phone;

import java.util.List;
public interface AccessService {
    List<Accessory> findAllAccess();
    Accessory getAccessByID(int id);
    List<Accessory> getAccessByBrand(int id);
    List<Accessory> searchAcc(String name);
}