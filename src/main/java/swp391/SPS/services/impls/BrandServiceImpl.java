package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Brand;
import swp391.SPS.repositories.BrandRepository;
import swp391.SPS.services.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand getBrand(int b) {
        return brandRepository.getReferenceById(b);
    }

    @Override
    public List<Brand> findAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public void addBrand(Brand b) {
        brandRepository.save(b);
    }

    @Override
    public void editBrand(Brand b) {
      Brand existingBrand = brandRepository.getReferenceById(b.getBrandId());
      existingBrand.setBrandName(b.getBrandName());
      brandRepository.save(existingBrand);
    }



}
