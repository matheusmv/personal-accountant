package br.edu.ifce.matheus.pacc.domain.ports.driven;

public interface PasswordEncoder {
    String encode(String password);
}
