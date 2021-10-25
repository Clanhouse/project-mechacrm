package com.crm.util;

import com.crm.dto.ActivationEmail;
import com.crm.model.db.AccountEntity;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

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

    @Async
    public void sendEmail(final AccountEntity accountEntity, final String token) {
        ActivationEmail activationEmail = generateEmail(accountEntity, token);

        try {
            Email email = new SimpleEmail();
            email.setHostName(hostName);
            email.setSmtpPort(smtpPort);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(sslOnConnect);
            email.setFrom(userName);
            email.setSubject(activationEmail.getSubject());
            email.setMsg(activationEmail.getMessage());
            email.addTo(activationEmail.getRecipient());
            log.info("Sending email to " + activationEmail.getRecipient());
            email.send();
            log.info("Sending email succeed");
        } catch (EmailException e) {
            log.error(e.getMessage());
        }
    }

    private ActivationEmail generateEmail(final AccountEntity accountEntity, final String token) {
        return ActivationEmail.builder()
                .recipient(accountEntity.getEmail())
                .subject("Hey " + accountEntity.getLogin() + ". Welcome in our CRM system.")
                .message("Activate your new account by clicking the link: " + endPointPath + token)
                .message("Activate your new account by clicking the link: " +
                        fromUriString(endPointPath).queryParam("token", token).build().toUri())
                .build();
    }
}
