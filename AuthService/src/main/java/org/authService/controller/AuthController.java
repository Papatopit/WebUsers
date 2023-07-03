package org.authService.controller;


import com.fasterxml.jackson.databind.JsonNode;
import org.authService.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private ProxyService roleService;

    @GetMapping("/role/{userId}")
    public ResponseEntity<JsonNode> getAddressByEmployeeId(@PathVariable("userId") Long userId) {
        JsonNode jsonNode = roleService.findRoleByUserId(userId);
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
