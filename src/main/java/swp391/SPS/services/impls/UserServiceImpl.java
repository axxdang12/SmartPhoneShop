package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import swp391.SPS.dtos.PageDto;
import swp391.SPS.dtos.ProfileDto;
import swp391.SPS.dtos.UserDto;
import swp391.SPS.entities.Cart;
import swp391.SPS.entities.Role;
import swp391.SPS.entities.User;
import swp391.SPS.exceptions.NoDataInListException;
import swp391.SPS.exceptions.OutOfPageException;
import swp391.SPS.exceptions.UserNotFoundException;
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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByOrderId(int orderId) {
        return userRepository.getUserByOrderId(orderId);
    }

    @Override
    public ResponseEntity getListUser(int page, int size) throws NoDataInListException {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userRequest = userRepository.findAllUser(pageable);
        if (userRequest.getContent().isEmpty()) {
            throw new NoDataInListException("No user");
        }
        if (page > userRequest.getTotalPages() - 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user");
        }
        return ResponseEntity.status(HttpStatus.OK).body(PageDto.builder().resultList(userRequest.getContent()).currentPage(userRequest.getNumber() + 1).totalPage(userRequest.getTotalPages()));
    }

    @Override
    public PageDto getListUserFirstLoad(int page, int size) throws NoDataInListException, OutOfPageException {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userRequest = userRepository.findAllUser(pageable);
        if (userRequest.getContent().isEmpty()) {
            throw new NoDataInListException("No user");
        }
        if (page > userRequest.getTotalPages() - 1) {
            throw new OutOfPageException("Out of page");
        }
        return PageDto.builder().resultList(userRequest.getContent()).currentPage(userRequest.getNumber() + 1).totalPage(userRequest.getTotalPages()).size(2).build();
    }

    @Override
    public void saveUserRole(int userId, String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        User user = new User();
        if (userRepository.findById(userId).isPresent()){
            user = userRepository.findById(userId).get();
        }
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
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
    public User saveProfile(ProfileDto profileDto, String userName) {
        User user = userRepository.findByUsername(userName);
        user.getUserDetail().setFirstName(profileDto.getFirstName());
        user.getUserDetail().setLastName(profileDto.getLastName());
        user.getUserDetail().setGender(profileDto.getGender());
        user.getUserDetail().setPhoneNumber(profileDto.getPhoneNumber());
        user.getUserDetail().setAddress(profileDto.getAddress());
        return userRepository.save(user);
    }

    @Override
    public User saveProfileCheckout(ProfileDto profileDto, String userName) {
        User user = userRepository.findByUsername(userName);
        user.getUserDetail().setFirstName(profileDto.getFirstName());
        user.getUserDetail().setLastName(profileDto.getLastName());
        user.getUserDetail().setPhoneNumber(profileDto.getPhoneNumber());
        user.getUserDetail().setAddress(profileDto.getAddress());
        return userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
