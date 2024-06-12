package swp391.SPS.services;

import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.User;
import swp391.SPS.exceptions.UserNotFoundException;

public interface UserService {
    User save(UserDto userDto);

    User findByUsername(String username);

    int getUserId(String userName);

    void updateResetPasswordToken(String token, String email) throws UserNotFoundException;

    public User getByResetPasswordToken(String token);

    public void updatePassword(User user, String newPassword);
}
