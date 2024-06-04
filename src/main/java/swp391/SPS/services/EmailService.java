package swp391.SPS.services;

import org.springframework.beans.factory.annotation.Autowired;
import swp391.SPS.entities.EmailDetails;

public interface EmailService {
  String sendSimpleMail(EmailDetails details);
}
