package br.edu.ifce.matheus.pacc.domain.ports;

public interface PasswordEncoder {
    String encode(String password);
}
