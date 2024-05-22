package swp391.SPS.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuthenDto implements UserDetails {

    private String password;
    private String userName;
    private GrantedAuthority grantedAuthority;

    public UserAuthenDto() {
    }

    public UserAuthenDto(String password, String userName,
                         GrantedAuthority grantedAuthority) {
        super();
        this.password = password;
        this.userName = userName;
        this.grantedAuthority = grantedAuthority;
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
