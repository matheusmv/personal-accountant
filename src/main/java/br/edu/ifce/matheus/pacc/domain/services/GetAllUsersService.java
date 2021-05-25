package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetAllUsers;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAllUsersService implements GetAllUsers {

    private final UserRepository userRepository;

    @Override
    public Page<User> execute(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return userRepository.findAllUsers(paging);
    }
}
