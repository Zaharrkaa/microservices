package ru.zaharka.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.zaharka.entities.UserCredential;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomDetails implements UserDetails {

    private final UserCredential userCredential;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.userCredential.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userCredential.getName();
    }
}
