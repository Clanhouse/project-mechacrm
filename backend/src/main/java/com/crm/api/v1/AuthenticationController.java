package com.crm.api.v1;

import com.crm.dto.request.AuthenticationRequest;
import com.crm.dto.response.JwtResponse;
import com.crm.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/auth")
    @ApiOperation(value = "Authenticate user and return JWT", notes = "Add account object to request body")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(loginService.authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
    }
}
