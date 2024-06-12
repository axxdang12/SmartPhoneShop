package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.User;
import swp391.SPS.exceptions.UserNotFoundException;
import swp391.SPS.repositories.RoleRepository;
import swp391.SPS.repositories.UserRepository;
import swp391.SPS.services.UserService;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public int getUserId(String userName) {
        return userRepository.findByUsername(userName).getUserId();
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
