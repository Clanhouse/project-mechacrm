package com.crm.api.v1;

import com.crm.dto.request.AccountRequest;
import com.crm.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;

    public AccountController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @PostMapping
    public String saveUser(@RequestBody final AccountRequest accountRequest) {
        return accountServiceImpl.saveAccount(accountRequest).toString();
    }
}
