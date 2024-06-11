package swp391.SPS.services;

import swp391.SPS.entities.Accessory;

import java.util.List;
public interface AccessService {
    List<Accessory> findAllAccess();
    List<Accessory> getAccessByBrand(int id);
    List<Accessory> getAccessByCategory(int id);
}