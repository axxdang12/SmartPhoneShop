package swp391.SPS.services;

import swp391.SPS.dtos.ProfileDto;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.Phone;
import swp391.SPS.entities.User;

public interface UserService {
  User save(UserDto userDto);

  User saveProfile(ProfileDto profileDto, String userName);
  User findByUsername(String username);
    int getUserId(String userName);
}
