package ir.maktab.testmaker.config.security;

import ir.maktab.testmaker.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType().name()));
        /*if (!user.getRoles().isEmpty() && user.getRoles() != null) {
            user.getRoles()
                    .forEach(role -> {
                                authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName())
                                ));
                                /*
                                if (!role.getOperations().isEmpty() && role.getOperations() != null) {
                                    role.getOperations().forEach(operation ->
                                            authorities.add(new SimpleGrantedAuthority(operation.getName())));
                                }
                            }
                    );
        }*/
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
