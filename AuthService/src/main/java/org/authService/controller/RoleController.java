package org.authService.controller;

import org.authService.response.RoleResponse;
import org.authService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{userId}")
    public ResponseEntity<RoleResponse> getAddressByEmployeeId(@PathVariable("userId") Long userId) {
        RoleResponse roleResponse = roleService.findRoleByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(roleResponse);
    }
}
