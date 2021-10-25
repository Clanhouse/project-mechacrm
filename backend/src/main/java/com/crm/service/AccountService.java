package com.crm.service;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;

public interface AccountService {

    void save(final NewAccountRequest newAccountRequest);

    AccountEntity findById(final Long id);

    AccountEntity findByLogin(final String login);

    boolean accountExistsByLogin(final String login);

    boolean accountExistsByEmail(final String email);

    AccountResponse activateAccount(final String token);
}
