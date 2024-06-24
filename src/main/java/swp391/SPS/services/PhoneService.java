package swp391.SPS.services;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import swp391.SPS.dtos.PageDto;
import swp391.SPS.entities.Phone;
import swp391.SPS.exceptions.NoDataInListException;
import swp391.SPS.exceptions.OutOfPageException;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PhoneService {
    List<Phone> findAllPhone();
    void addPhone(Phone phone);
    Phone getPhoneByID(int id);
    List<Phone> getPhoneByBrand(int id);

    void editPhone(Phone p);
    void changeStatus(Phone p);
    List<Phone> searchPhone(String name);
//    PageDto getListProductFirstLoad(int page, int size) throws OutOfPageException;
//    ResponseEntity getListProduct(int page, int size) throws NoDataInListException;
//    Page<Phone> Pagination(Pageable pageable);
    Page<Phone> findPhonePage(int pageNo);
    Page<Phone> searchPhone(String name, int pageNo);
}
