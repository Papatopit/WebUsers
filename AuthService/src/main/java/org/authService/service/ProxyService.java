package org.authService.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ProxyService {

    @Autowired
    private WebClient webClient;

    public JsonNode findRoleByUserId(Long userId){
        JsonNode jsonNode = webClient.get().uri("/user/" + userId)
                .retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }
}
