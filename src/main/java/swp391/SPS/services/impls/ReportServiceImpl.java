package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Order;
import swp391.SPS.entities.Report;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.*;
import swp391.SPS.services.OrderService;
import swp391.SPS.services.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private OrderService orderService;

    @Override
    public Report submitR(int orderId, String description, User user) {
        Order order=orderService.getOrder(orderId);
        Report report=new Report();
        report.setOrder(order);
        report.setUser(user);
        report.setDescription(description);
        report.setStatus("Pending");
        return reportRepository.save(report);
    }

    @Override
    public Report getReportByOrderId(int orderId) {
        return reportRepository.getReportFromOrder(orderId);
    }

    @Override
    public void deleteReport(int report) {
        reportRepository.deleteR(report);
    }

    @Override
    public Report getReport(int reportId) {
        return reportRepository.getReferenceById(reportId);
    }
}
