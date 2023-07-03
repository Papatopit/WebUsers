package org.authService.controller;

import lombok.extern.slf4j.Slf4j;
import org.authService.response.UserResponse;
import org.authService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users/{id}")
    private ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") Long id){
        UserResponse userResponse = userService.getUserBuId(id);
        log.info("Get User Details from UserController: {}",userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    @GetMapping("/role/{userId}")
    public ResponseEntity<UserResponse> getAddressByEmployeeId(@PathVariable("userId") Long userId) {
        UserResponse userResponse = userService.findRoleByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}
