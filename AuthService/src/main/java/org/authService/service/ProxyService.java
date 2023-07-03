package org.authService.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authService.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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

    public JsonNode getAllUsers(){
        JsonNode jsonNode = webClient.get().uri("/all").retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }

    public JsonNode getUserDetails(Long id){
        JsonNode jsonNode = webClient.get().uri("/user/{id}" + id)
                .retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }

    public JsonNode createUser( User user){
        JsonNode jsonNode = webClient.get().uri("/create" + user)
                .retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }

    public JsonNode updateUser(Long id, User user){
        JsonNode jsonNode = webClient.get().uri("/update/{id}" + id, user)
                .retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }

    public JsonNode deleteUserById(Long id){
        JsonNode jsonNode = webClient.get().uri("/delete/{id}" + id)
                .retrieve().bodyToMono(JsonNode.class).block();
        return jsonNode;
    }

}
