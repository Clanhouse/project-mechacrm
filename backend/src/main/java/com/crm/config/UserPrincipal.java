package com.crm.config;

import com.crm.model.db.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final AccountEntity accountEntity;

    public UserPrincipal(final AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (accountEntity.getRole() == null) {
            return Collections.emptyList();
        } else
            return Collections.singletonList(new SimpleGrantedAuthority(accountEntity.getRole().getName()));
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
        //TODO
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountEntity.getIsActive();
    }
}
