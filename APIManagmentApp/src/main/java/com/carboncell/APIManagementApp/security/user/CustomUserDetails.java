package com.carboncell.APIManagementApp.security.user;
import com.carboncell.APIManagementApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private Collection<GrantedAuthority>authorities;

    public CustomUserDetails (User user){
        email = user.getEmail();
        password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority>getAuthorities(){
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
