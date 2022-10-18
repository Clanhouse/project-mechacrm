package io.tribehouse.motomo.security;

import io.tribehouse.motomo.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final io.tribehouse.motomo.user.User client = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found"));
        return new User(username, client.getPassword(), client.getAuthorities());
    }
}
