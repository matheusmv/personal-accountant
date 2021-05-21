package br.edu.ifce.matheus.pacc.domain.ports.driven;

import lombok.Getter;
import lombok.Setter;

public interface EmailService {
    void execute(EmailDetails details);

    @Getter
    @Setter
    class EmailDetails {
        private String to;
        private String recipientName;
        private String recipientUsername;
        private String confirmationLink;
    }
}
