package org.authService.controller;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.authService.auth.AuthenticationRequest;
import org.authService.auth.AuthenticationResponse;
import org.authService.service.AuthenticationService;
import org.authService.service.ProxyService;
import org.authService.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthController {
    @Autowired
    private ProxyService proxyService;

    @Autowired
    private AuthenticationService service;

    @GetMapping("/role/{userId}")
    public ResponseEntity<JsonNode> getRoleByUserId(@PathVariable("userId") Long userId) {
        JsonNode jsonNode = proxyService.findRoleByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/all")
    public ResponseEntity<JsonNode> getAllUsers() {
        JsonNode jsonNode = proxyService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<JsonNode> getUserDetails(@PathVariable("id") Long id) {
        JsonNode jsonNode = proxyService.getUserDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }

    @PutMapping("/create")
    public ResponseEntity<JsonNode> createUser(@RequestBody User user) {
        JsonNode jsonNode = proxyService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonNode);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<JsonNode> updateUser(@PathVariable("id")Long id, @RequestBody User user) {
        JsonNode jsonNode = proxyService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JsonNode> deleteUserById(@PathVariable("id") Long id) {
        JsonNode jsonNode = proxyService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
    }
}
