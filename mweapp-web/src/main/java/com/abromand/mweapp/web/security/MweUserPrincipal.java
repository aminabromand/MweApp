package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MweUserPrincipal implements UserDetails {

    private final MweUser mweUser;

    public MweUserPrincipal(MweUser mweUser) {
        this.mweUser = mweUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(mweUser.isCsb()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CSB"));
        }

        if(mweUser.isCto()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CTO"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return mweUser.getPassword();
    }

    @Override
    public String getUsername() {
        return mweUser.getUsername();
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
