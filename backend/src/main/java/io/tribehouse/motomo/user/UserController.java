package io.tribehouse.motomo.user;

import io.tribehouse.motomo.security.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping
@RestController
public class UserController {

    private final UserService userService;
    private final String USER_CREATED_MESSAGE = "Successfully created new user.";
    private final String CANNOT_CREATE_MESSAGE = "Unable to create user.";
    private final String ALREADY_EXIST_MESSAGE = "User already exist, choose another email.";
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody @Valid final LoginCredentials loginCredentials) {
        boolean userAlreadyExist = userService.checkIfUserExist(loginCredentials.getEmail());
        if (!userAlreadyExist) {
            User newlyCreatedUser = userService.createUser(loginCredentials.getEmail(), passwordEncoder.encode(loginCredentials.getPassword()));
            return (newlyCreatedUser != null) ? new ResponseEntity<>(USER_CREATED_MESSAGE, HttpStatus.CREATED) : new ResponseEntity<>(CANNOT_CREATE_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(ALREADY_EXIST_MESSAGE, HttpStatus.SEE_OTHER);
        }
    }
}

