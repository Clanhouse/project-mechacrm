package com.crm.api.v1;

import com.crm.dto.request.AuthenticationRequest;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.service.AccountService;
import com.crm.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;
    private final AccountService accountService;

    @PostMapping("/auth")
    @ApiOperation(value = "Authenticate user and return JWT", notes = "Add account object to request body")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(loginService.authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create and save new Account in database", notes = "Add new account object to request body")
    public ResponseEntity<AccountResponse> createNewAccount(@RequestBody @Valid final NewAccountRequest newAccountRequest) {
        return ResponseEntity.ok(accountService.save(newAccountRequest));
    }

    @GetMapping("/activate/{token}")
    @ApiOperation(value = "Activate account confirmed by email", notes = "Add user's account id to path")
    public ResponseEntity<AccountResponse> activateAccount(@PathVariable final String token) {
        return ResponseEntity.ok(accountService.activateNewAccount(token));
    }


}
