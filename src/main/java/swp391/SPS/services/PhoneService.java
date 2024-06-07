package swp391.SPS.services;
import swp391.SPS.entities.Category;
import swp391.SPS.entities.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> findAllPhone();
    void addPhone(Phone phone);
    Phone getPhoneByID(int id);
    List<Phone> getPhoneByBrand(int id);
    List<Phone> getPhoneByCategory(int id);
}
