package swp391.SPS.services.impls;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Role;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.RoleRepository;
import swp391.SPS.repositories.UserRepository;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  private boolean isExist(String userName) {
    if (userRepository.findByUserName(userName).isPresent()) {
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!isExist(username)) {
      try {
        throw new AccountNotFoundException();
      } catch (AccountNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    UserDetailImpl userDetail = UserDetailImpl.build(userRepository.findByUserName(username).get());

    return userDetail;
  }
}
