package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import swp391.SPS.dtos.UserAuthenDto;
import swp391.SPS.entities.Role;
import swp391.SPS.entities.User;
import swp391.SPS.repositories.RoleRepository;
import swp391.SPS.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Role role = roleRepository.findRole(user.getUserId());

        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "";
            }
        };

        if (role != null){
            grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
        }

        return new UserAuthenDto(user.getPassword(), user.getUserName(), grantedAuthority);
    }
}
