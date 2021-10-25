package com.crm.util;

import com.crm.dto.MyEmail;
import com.crm.model.db.AccountEntity;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class EmailUtils {

    @Value("${email.endpoint.path}")
    private String endPointPath;

    @Value("${email.host.name}")
    private String hostName;

    @Value("${email.smtp.port}")
    private int smtpPort;

    @Value("${email.user.name}")
    private String userName;

    @Value("${email.user.password}")
    private String password;

    @Value("${email.ssl.connect}")
    private boolean sslOnConnect;


    private MyEmail generateEmail(final AccountEntity accountEntity, final String token) {
        return MyEmail.builder()
                .recipient(accountEntity.getEmail())
                .subject("Hey " + accountEntity.getLogin() + ". Welcome in our CRM system.")
                .message("Activate your new account by clicking the link: " + endPointPath + token)
                .build();
    }

    public void sendEmail(final AccountEntity accountEntity, final String token) {
        MyEmail myEmail = generateEmail(accountEntity, token);

        try {
            Email email = new SimpleEmail();
            email.setHostName(hostName);
            email.setSmtpPort(smtpPort);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(sslOnConnect);
            email.setFrom(userName);
            email.setSubject(myEmail.getSubject());
            email.setMsg(myEmail.getMessage());
            email.addTo(myEmail.getRecipient());
            log.info("Sending email to " + myEmail.getRecipient());
            email.send();
            log.info("Sending email succeed");
        } catch (EmailException e) {
            log.error(e.getMessage());
            // todo throw new SendVerificationMailException("mail nie został wysłany");
        }
    }
}
