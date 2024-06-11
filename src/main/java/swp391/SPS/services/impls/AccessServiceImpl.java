package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Brand;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.AccessRepository;
import swp391.SPS.repositories.BrandRepository;
import swp391.SPS.services.AccessService;

import java.util.ArrayList;
import java.util.List;
@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    AccessRepository accessRepository;
    @Autowired
    BrandRepository brandRepository;


    @Override
    public List<Accessory> findAllAccess() {
        return accessRepository.findAll();
    }

    @Override
    public Accessory getAccessByID(int id) {
        return accessRepository.getReferenceById(id);
    }

    @Override
    public List<Accessory> getAccessByBrand(int id) {

        Brand brand = brandRepository.getReferenceById(id);
        List<Accessory> l = new ArrayList<Accessory>();
        for (int i = 0; i < findAllAccess().size(); i++) {
            if(findAllAccess().get(i).getBrand().equals(brand)) l.add(findAllAccess().get(i));
        }
        return l;
    }

    @Override
    public List<Accessory> searchAcc(String name) {
        return accessRepository.SearchAccess(name);
    }
}