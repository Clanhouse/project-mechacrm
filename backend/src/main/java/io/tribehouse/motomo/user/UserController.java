package io.tribehouse.motomo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //TODO utworzyć klasę statyczną i przenieść do niej
/*    private final String USER_CREATED_MESSAGE = "Successfully created new user.";
    private final String ALREADY_EXIST_MESSAGE = "User already exist, choose another email.";*/

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid final UserRequest userRequest) {
        UserDto userDto = UserMapper.INSTANCE.userRequestToUserDto(userRequest);
        UserDto returnedUser = userService.createUser(userDto);
        if (returnedUser != null) {
            UserResponse response = UserMapper.INSTANCE.userDtoToUserResponse(returnedUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.SEE_OTHER);
        }
    }
}

