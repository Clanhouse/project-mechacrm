package com.crm.service;

import com.crm.model.db.AccountEntity;
import com.crm.model.db.VerificationTokenEntity;

public interface VerificationTokenService {

    String saveVerificationToken(AccountEntity accountEntity);

    VerificationTokenEntity isTokenNotExpired(String token);
}
