package com.crm.api.v1;

import com.crm.dto.request.AccountRequest;
import com.crm.model.db.AccountEntity;
import com.crm.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public String saveUser(@RequestBody AccountRequest accountRequest) {
        return accountService.saveAccount(accountRequest).toString();
    }
}
