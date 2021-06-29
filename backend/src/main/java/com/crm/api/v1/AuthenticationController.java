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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final AccountService accountService;
    private final LoginService loginService;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final AccountRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        return ResponseEntity.ok(new JwtResponse(jwtTokenUtils.generateToken(userDetails), jwtTokenUtils.generateRefreshToken(userDetails)));
    }

    @ApiOperation(value = "Create and save new Account in database")
    @PostMapping("/newAccount")
    public ResponseEntity<?> createNewAccount(@RequestBody @Valid final NewAccountRequest newAccountRequest) {
        final AccountResponse save = accountService.save(newAccountRequest);
        return ResponseEntity.ok(save);
    }


    private void authenticate(final String username, final String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            loginService.resetAttemptsCounter(username);
        } catch (final DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (final BadCredentialsException e) {
            loginService.increaseAttemptsCounter(username);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}