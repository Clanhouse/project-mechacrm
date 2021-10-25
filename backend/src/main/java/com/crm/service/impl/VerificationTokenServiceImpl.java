package com.crm.service.impl;

import com.crm.exception.TokenExpiredException;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.VerificationTokenEntity;
import com.crm.repository.VerificationTokenRepository;
import com.crm.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.crm.exception.ErrorDict.TOKEN_EXPIRED;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public String saveVerificationToken(AccountEntity accountEntity) {
        deleteExpiredTokens(accountEntity.getId());

        String token = UUID.randomUUID().toString();

        VerificationTokenEntity verificationTokenEntity = VerificationTokenEntity.builder()
                .user(accountEntity)
                .token(token)
                .expiryDate(calculateExpiryDate(24))
                .build();

        VerificationTokenEntity vte = verificationTokenRepository.save(verificationTokenEntity);
        return vte.getToken();
    }

    @Override
    public VerificationTokenEntity isTokenNotExpired(String token){
        VerificationTokenEntity vte = verificationTokenRepository.findByToken(token).orElseThrow(() -> new TokenExpiredException(TOKEN_EXPIRED));
        Timestamp tokenDate = vte.getExpiryDate();

        if(tokenDate.before(Timestamp.from(Instant.now()))){
            throw new TokenExpiredException(TOKEN_EXPIRED);
        }

        return vte;
    }

    private Timestamp calculateExpiryDate(int expiryTimeInHours) {
        Timestamp start = Timestamp.from(Instant.now());
        return new Timestamp(start.getTime() + TimeUnit.HOURS.toMillis(expiryTimeInHours));
    }

    public void deleteExpiredTokens(Long userId){
        verificationTokenRepository.deleteByUserId(userId);
    }
}
