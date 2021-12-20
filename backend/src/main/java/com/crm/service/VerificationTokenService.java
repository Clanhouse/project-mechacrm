package com.crm.service;

import com.crm.model.db.AccountEntity;
import com.crm.model.db.VerificationTokenEntity;

public interface VerificationTokenService {

    String saveVerificationToken(final AccountEntity accountEntity);

    VerificationTokenEntity getVerificationToken(final String token);
}
