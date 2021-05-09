package com.crm.service;

import com.crm.dto.request.AccountRequest;
import com.crm.model.db.AccountEntity;

public interface AccountService {

    AccountEntity saveAccount(AccountRequest accountRequest);
}
