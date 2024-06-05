package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Brand;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.BrandRepository;
import swp391.SPS.repositories.PhoneRepository;
import swp391.SPS.services.PhoneService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private BrandRepository brandRepository;


    @Override
    public List<Phone> findAllPhone() {
        return phoneRepository.findAll();
    }

    @Override
    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public Phone getPhoneByID(int id) {
       Phone p =  phoneRepository.getReferenceById(id);
       return p;
    }

    @Override
    public List<Phone> getPhoneByBrand(int id) {
        Brand brand = brandRepository.getReferenceById(id);
        List<Phone> l = new ArrayList<Phone>();
        for (int i = 0; i < findAllPhone().size(); i++) {
            if(findAllPhone().get(i).getBrand().equals(brand)) l.add(findAllPhone().get(i));
        }
        return l;
    }
}
