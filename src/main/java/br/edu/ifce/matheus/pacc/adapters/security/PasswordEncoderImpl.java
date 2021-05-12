package br.edu.ifce.matheus.pacc.adapters.security;

import br.edu.ifce.matheus.pacc.domain.ports.driven.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
