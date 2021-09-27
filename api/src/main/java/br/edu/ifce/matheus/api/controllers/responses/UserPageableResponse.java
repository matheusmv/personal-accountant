package br.edu.ifce.matheus.api.controllers.responses;

import br.edu.ifce.matheus.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserPageableResponse {
    private List<UserProfileResponse> users;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public UserPageableResponse(Page<User> page) {
        this.users = page.getContent()
                .stream()
                .map(UserProfileResponse::new)
                .collect(Collectors.toList());
        this.currentPage = page.getNumber();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
