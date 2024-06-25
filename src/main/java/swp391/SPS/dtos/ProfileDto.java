package swp391.SPS.dtos;

import jakarta.validation.constraints.NotBlank;
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
  @NotBlank(message = "First name is required")
  private String firstName;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "^0\\d{8,}$", message = "Phone number must start with 0 and have at least 9 digits")
  private String phoneNumber;
  private String email;
  private String gender;

  @NotBlank(message = "Address is required")
  private String address;
}
