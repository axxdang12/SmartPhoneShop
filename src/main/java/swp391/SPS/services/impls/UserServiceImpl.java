package swp391.SPS.services.impls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.repositories.UserRepository;
import swp391.SPS.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int getUserId(String userName) {
        return userRepository.findByUsername(userName).getUserId();
    }
}
