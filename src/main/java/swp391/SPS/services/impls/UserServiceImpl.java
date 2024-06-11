package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Role;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.RoleRepository;
import swp391.SPS.repositories.UserRepository;
import swp391.SPS.services.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Role role = roleRepository.findByRoleName("USER");
        role.setUsers(Collections.singletonList(user));
        user.setRoles(Collections.singletonList(role));
        Cart cart = new Cart();
        user.setCart(cart);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
