package io.tribehouse.motomo.user;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**Every created account gets User role by default.*/
    public User createUser(String email, String encodedPassword) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public boolean checkIfUserExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
