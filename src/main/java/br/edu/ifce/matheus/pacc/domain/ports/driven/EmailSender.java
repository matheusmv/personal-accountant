package br.edu.ifce.matheus.pacc.domain.ports.driven;

public interface EmailSender {
    void execute(String recipient, String confirmationLink, String token);
}
