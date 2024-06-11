package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.dtos.ProfileDto;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.User;
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
    public User save(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(Arrays.asList(roleRepository.findByRoleName("ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public User saveProfile(ProfileDto profileDto, String userName) {
        User user=userRepository.findByUsername(userName);
        user.getUserDetail().setFirstName(profileDto.getFirstName());
        user.getUserDetail().setLastName(profileDto.getLastName());
        user.getUserDetail().setGender(profileDto.getGender());
        user.getUserDetail().setPhoneNumber(profileDto.getPhoneNumber());
        user.getUserDetail().setAddress(profileDto.getAddress());
        return userRepository.save(user);
    }



    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
