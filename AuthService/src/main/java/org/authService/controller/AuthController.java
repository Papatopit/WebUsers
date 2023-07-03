package org.authService.controller;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.authService.auth.AuthenticationRequest;
import org.authService.auth.AuthenticationResponse;
import org.authService.service.AuthenticationService;
import org.authService.service.ProxyService;
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
    public ResponseEntity<JsonNode> getAddressByEmployeeId(@PathVariable("userId") Long userId) {
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
}
