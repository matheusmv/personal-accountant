package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.User;
import br.edu.ifce.matheus.usecase.exceptions.ValidationException;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driver.GetAllUsers;
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
        validatePage(page);

        Pageable paging = PageRequest.of(page, size);

        return userRepository.findAllUsers(paging);
    }

    private void validatePage(int page) {
        if (page < 0) {
            throw new ValidationException("Page index must not be less than zero.");
        }
    }
}
