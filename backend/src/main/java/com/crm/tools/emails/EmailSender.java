package com.crm.tools.emails;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender {

    @Value("${email.host.name}")
    private String hostName;
    @Value("${email.smtp.port}")
    private int smtpPort;
    @Value("${email.user.name}")
    private String userName;
    @Value("${email.user.password}")
    private String password;
    @Value("${email.ssl.on.connect}")
    private boolean sslOnConnect;

    public void sendEmail(MyEmail myEmail) {
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
        }
    }
}
