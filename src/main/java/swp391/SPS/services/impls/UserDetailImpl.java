package swp391.SPS.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.User;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailImpl implements UserDetails {

  private String password;
  private String userName;
  private static SimpleGrantedAuthority authority;

  public UserDetailImpl() {}

  public UserDetailImpl(String password, String userName, GrantedAuthority grantedAuthority) {
    super();
    this.password = password;
    this.userName = userName;
    this.authority = authority;
  }

  public static UserDetailImpl build(User user) {
    authority = new SimpleGrantedAuthority(user.getRole().getRoleName());

    return new UserDetailImpl(user.getUserName(), user.getPassword(), authority);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
