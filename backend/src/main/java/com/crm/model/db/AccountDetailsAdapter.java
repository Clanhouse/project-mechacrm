package com.crm.model.db;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class AccountDetailsAdapter implements UserDetails {

    private final AccountEntity accountEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (accountEntity.getRole() == null) {
            return Collections.emptyList();
        } else
            return List.of(new SimpleGrantedAuthority(accountEntity.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return accountEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return accountEntity.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountEntity.getIsActivated();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountEntity.getIsActivated();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return accountEntity.getIsActivated();
    }

    @Override
    public boolean isEnabled() {
        return accountEntity.getIsActivated();
    }
}
