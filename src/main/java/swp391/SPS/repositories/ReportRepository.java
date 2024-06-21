package swp391.SPS.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.SPS.entities.Brand;
import swp391.SPS.entities.Report;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
//    List<Report> findByUserId(Long userId);
//    List<Report> findByStatus(String status);
}
