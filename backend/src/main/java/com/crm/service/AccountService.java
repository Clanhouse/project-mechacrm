package com.crm.service;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;

public interface AccountService {

    AccountResponse save(NewAccountRequest newAccountRequest);

    AccountEntity findById(final Long id);

    boolean isAccountExist(final String login);

    boolean isAccountEmail(final String email);

    AccountResponse activateNewAccount(final String token);
}
