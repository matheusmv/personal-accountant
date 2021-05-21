package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.ports.driven.EmailService;
import br.edu.ifce.matheus.pacc.domain.ports.driver.SendConfirmationEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendConfirmationEmailService implements SendConfirmationEmail {

    private final EmailService emailService;

    @Override
    public void execute(User user, String confirmationLink) {
        var name = user.getName();
        var username = user.getUsername();
        var userEmail = user.getEmail();
        var userToken = user.getConfirmationToken().getToken();

        var details = new EmailService.EmailDetails();

        details.setTo(userEmail);
        details.setRecipientName(name);
        details.setRecipientUsername(username);
        details.setConfirmationLink(confirmationLink + userToken);

        emailService.execute(details);
    }
}
