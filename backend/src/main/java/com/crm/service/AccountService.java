package com.crm.service;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;

public interface AccountService {
    AccountResponse save(final NewAccountRequest newAccountRequest);

    AccountEntity findById(final Long id);

    AccountEntity findByLogin(final String login);
}
