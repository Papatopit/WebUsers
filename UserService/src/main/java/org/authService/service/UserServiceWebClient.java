package org.authService.service;

import org.authService.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class UserServiceWebClient {
    @Autowired
    private WebClient webClient;

    public UserResponse findRoleByUserId(Long userId) {
        UserResponse userResponse = webClient.get().uri("/user/" + userId)
                .retrieve().bodyToMono(UserResponse.class).block();

        return userResponse;
    }
}
