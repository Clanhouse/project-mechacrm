package com.crm.api.v1;

import com.crm.config.JwtTokenUtils;
import com.crm.dto.request.AccountRequest;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.dto.response.JwtResponse;
import com.crm.service.AccountService;
import com.crm.service.JwtUserDetailsService;
import com.crm.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtTokenUtils jwtTokenUtils;
    private final JwtUserDetailsService userDetailsService;
    private final AccountService accountService;
    private final LoginService loginService;


    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final AccountRequest authenticationRequest) {
        loginService.authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        return ResponseEntity.ok(new JwtResponse(jwtTokenUtils.generateToken(userDetails), jwtTokenUtils.generateRefreshToken(userDetails)));
    }

    @ApiOperation(value = "Create and save new Account in database")
    @PostMapping("/register")
    public ResponseEntity<?> createNewAccount(@RequestBody @Valid final NewAccountRequest newAccountRequest) {
        return ResponseEntity.ok(accountService.save(newAccountRequest));
    }

    @ApiOperation(value = "Activate new Account")
    @PostMapping("/activate/{id}")
    public ResponseEntity<AccountResponse> activateAccountWithGivenId(@PathVariable @Min(0) final Long id) {
        return ResponseEntity.ok(accountService.activateNewAccount(id));
    }
}