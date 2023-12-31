package org.authService.controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.authService.service.ProxyService;
import org.authService.dto.RoleDto;
import org.authService.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AuthServiceController {
    @Autowired
    private ProxyService proxyService;

    @PutMapping("/rolechange/{id}")
    public ResponseEntity<Mono<JsonNode>> updateUserRole(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        Mono<JsonNode> jsonNode = proxyService.changeUserRole(id,roleDto);
        log.info("CHANGE User Role, user ID: {}",id);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<JsonNode>> getAllUsers() {
        Flux<JsonNode> jsonNode = proxyService.getAllUsers();
        log.info("GET All Users {}", proxyService.getAllUsers());
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<JsonNode> getUserDetails(@PathVariable("id") Long id) {
        JsonNode jsonNode = proxyService.getUserDetails(id);
        log.info("GET User By ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<JsonNode>> createUser(@RequestBody UserDto user) {
        Mono<JsonNode> jsonNode = proxyService.createUser(user);
        log.info("CREATE User: {}", proxyService.createUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonNode);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<JsonNode>> updateUser(@PathVariable("id")Long id, @RequestBody UserDto user) {
        Mono<JsonNode> jsonNode = proxyService.updateUser(id, user);
        log.info("UPDATE User {}", proxyService.updateUser(id, user));
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JsonNode> deleteUserById(@PathVariable("id") Long id) {
        JsonNode jsonNode = proxyService.deleteUserById(id);
        log.info("DELETE User By ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }
}
