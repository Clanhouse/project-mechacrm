package com.crm.tools.emails;


import com.crm.model.db.AccountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class EmailGenerator {

    @Value("${email.endpoint.path}")
    private String endPointPath;

    public MyEmail generateEmail(AccountEntity accountEntity) {
        return MyEmail.builder()
                .recipient(accountEntity.getEmail())
                .subject("Witaj " + accountEntity.getLogin() + " w aplikacji CRM")
                .message("Aktywuj swoje konto klikając w link: " + endPointPath + accountEntity.getId())
                .build();
    }

}
