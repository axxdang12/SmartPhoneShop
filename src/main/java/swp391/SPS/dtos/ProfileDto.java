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
  @Pattern(regexp = "^[a-zA-Z]$", message = "First Name must be word!")
  @NotEmpty(message = "First Name can not be blank")
  private String firstName;

  @Pattern(regexp = "^[a-zA-Z]$", message = "Last Name must be word!")
  @NotEmpty(message = "Last Name can not be blank")
  private String lastName;

  @Pattern(regexp = "^[0-9_-]{10}$", message = "Phone number must be 10 digits")
  @NotEmpty(message = "Phone can not be blank")
  private String phoneNumber;
  private String email;
  private String gender;

  @NotEmpty(message = "Address can not be blank")
  private String address;
}
