package swp391.SPS.services;

import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.User;

public interface UserService {
  User save(UserDto userDto);

  User findByUsername(String username);
}
