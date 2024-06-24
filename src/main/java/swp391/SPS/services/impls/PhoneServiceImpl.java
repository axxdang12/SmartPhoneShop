package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import swp391.SPS.dtos.PageDto;
import swp391.SPS.entities.Brand;
//import swp391.SPS.entities.Category;
import swp391.SPS.entities.Phone;
import swp391.SPS.entities.User;
import swp391.SPS.exceptions.NoDataInListException;
import swp391.SPS.exceptions.OutOfPageException;
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
            existingPhone.setStatus(p.getStatus());
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

    @Override
    public Page<Phone> findPhonePage(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1,5);
        return this.phoneRepository.findAll(pageable);

    }

    @Override
    public Page<Phone> searchPhone(String name, int pageNo) {
        List<Phone> list = phoneRepository.SearchProduct(name);
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        int start = (int) pageable.getOffset();
        int end = pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : (int) (pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<>(list, pageable, phoneRepository.SearchProduct(name).size());
    }


//    @Override
//    public PageDto getListProductFirstLoad(int page, int size) throws OutOfPageException {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Phone> userRequest = phoneRepository.findAllPhone(pageable);
//        if (userRequest.getContent().isEmpty()) {
//            try {
//                throw new NoDataInListException("No phone");
//            } catch (NoDataInListException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if (page > userRequest.getTotalPages() - 1) {
//            throw new OutOfPageException("Out of page");
//        }
//        return PageDto.builder().resultList(userRequest.getContent()).currentPage(userRequest.getNumber() + 1).totalPage(userRequest.getTotalPages()).build();
//
//    }
//
//    @Override
//    public ResponseEntity getListProduct(int page, int size) throws NoDataInListException {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Phone> userRequest = phoneRepository.findAllPhone(pageable);
//        if (userRequest.getContent().isEmpty()) {
//            throw new NoDataInListException("No phone");
//        }
//        if (page > userRequest.getTotalPages() - 1) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No phone");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(PageDto.builder().resultList(userRequest.getContent()).currentPage(userRequest.getNumber() + 1).totalPage(userRequest.getTotalPages()));
//
//    }
//
//    @Override
//    public Page<Phone> Pagination(Pageable pageable) {
//        return phoneRepository.findAllPhone(pageable);
//    }


}
