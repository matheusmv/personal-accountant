package br.edu.ifce.matheus.pacc.adapters.email;

import br.edu.ifce.matheus.pacc.domain.exceptions.EmailSenderException;
import br.edu.ifce.matheus.pacc.domain.ports.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final static String EMAIL_FROM = "no-reply@pacc.com";
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String destinationEmail, String emailBody) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(emailBody, true);
            helper.setTo(destinationEmail);
            helper.setSubject("Confirm your email");
            helper.setFrom(EMAIL_FROM);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new EmailSenderException("failed to send email");
        }
    }
}
