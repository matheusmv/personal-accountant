package br.edu.ifce.matheus.pacc.domain.ports;

public interface EmailSender {
    void send(String destinationEmail, String emailBody);
}
