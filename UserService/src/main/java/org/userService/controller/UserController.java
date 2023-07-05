package org.userService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.userService.entity.Role;
import org.userService.entity.User;
import org.userService.response.UserResponse;
import org.userService.service.UserServiceWebClient;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserServiceWebClient userServiceWebClient;


    @GetMapping("/all")
    private ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userServiceWebClient.getAllUsers();
        log.info("Get ALL USERS from UserController: {}", userList);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") Long id) {
        UserResponse userResponse = userServiceWebClient.getUserById(id);
        log.info("Get User Details with id {} from UserController: {}", id, userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/rolechange/{id}")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable("id") Long id, @RequestBody Role role) {
        UserResponse userResponse = userServiceWebClient.changeRoleForUser(id, role);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/create")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        User userResponse = userServiceWebClient.saveUser(user);
        log.info("CREATE Users from UserController: {}", userResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<UserResponse> updateUser(@PathVariable("id")Long id, @RequestBody User user) {
        UserResponse userResponse = userServiceWebClient.updateUser(id, user);
        log.info("UPDATE Users with id: {} from UserController: {}",id, userResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        boolean deleteUserById =  userServiceWebClient.deleteUserById(id);
        log.info("DELETE User with id {} from UserController", id);

        if (deleteUserById) {
            return new ResponseEntity<>(("User deleted - User ID:" + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("User deletion failed - User ID:" + id), HttpStatus.BAD_REQUEST);
        }
    }


}
