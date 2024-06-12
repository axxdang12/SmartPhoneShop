package swp391.SPS.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private String gender;
  private String address;
}
