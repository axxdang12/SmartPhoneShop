package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Brand;
//import swp391.SPS.entities.Category;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.BrandRepository;
import swp391.SPS.repositories.PhoneRepository;
//import swp391.SPS.repositories.CategoryRepository;
import swp391.SPS.services.PhoneService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private BrandRepository brandRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;


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

//    @Override
//    public List<Phone> getPhoneByCategory(int id) {
//        Category category = categoryRepository.getReferenceById(id);
//        List<Phone> l = new ArrayList<Phone>();
//        for (int i = 0; i < findAllPhone().size(); i++) {
//            if(findAllPhone().get(i).getCategory().equals(category)) l.add(findAllPhone().get(i));
//        }
//        return l;
//    }

    @Override
    public void editPhone(Phone p) {
        Phone existingPhone = phoneRepository.getReferenceById(p.getPhoneId());
        if (existingPhone != null) {
            existingPhone.setProductName(p.getProductName());
            existingPhone.setPrice(p.getPrice());
            existingPhone.setCpu(p.getCpu());
            existingPhone.setRam(p.getRam());
            existingPhone.setMemory(p.getMemory());
            existingPhone.setDisplay(p.getDisplay());
            existingPhone.setCamera(p.getCamera());
            existingPhone.setOrigin(p.getOrigin());
            existingPhone.setSim(p.getSim());
            existingPhone.setReleaseDate(p.getReleaseDate());
//            existingPhone.setCategory(p.getCategory());
            existingPhone.setBrand(p.getBrand());
            existingPhone.setPicture(p.getPicture());
            existingPhone.getPicture().setBack(p.getPicture().getBack());
            existingPhone.getPicture().setFront(p.getPicture().getFront());
            existingPhone.getPicture().setMain(p.getPicture().getMain());
            existingPhone.getPicture().setSite(p.getPicture().getSite());
            // Lưu đối tượng Phone đã được cập nhật vào cơ sở dữ liệu
            phoneRepository.save(existingPhone);

        }
    }
    @Override
    public void changeStatus(Phone p) {
        p.setStatus(!p.getStatus());
        phoneRepository.save(p);
    }

    @Override
    public List<Phone> searchPhone(String name) {
        return phoneRepository.SearchProduct(name);
    }
}
