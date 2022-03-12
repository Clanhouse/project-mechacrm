package com.crm.api.v1;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create and save new Account in database", notes = "Add new account object to request body")
    public ResponseEntity<Void> createAccountRequest(@RequestBody @Valid final NewAccountRequest newAccountRequest) {
        accountService.save(newAccountRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/account/activate")
    @ApiOperation(value = "Activate account by token from email confirmation", notes = "Add activation token from received email in path as parameter")
    public ResponseEntity<AccountResponse> activateAccount(@RequestParam("token") final String token) {
        return ResponseEntity.ok(accountService.activateAccount(token));
    }
}

