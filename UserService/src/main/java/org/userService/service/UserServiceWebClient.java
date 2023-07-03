package org.userService.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.userService.entity.User;
import org.userService.repository.UserRepository;
import org.userService.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceWebClient {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("get User By Id from UserService:  {}", user);
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        return userResponse;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        log.info("SAVE in DB user name: {}", user);
        return userRepository.save(user);
    }

    public UserResponse updateUser(Long id, User user) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setBirthday(user.getBirthday());
        updateUser.setRole(user.getRole());
        updateUser.setPassword(user.getPassword());
        UserResponse userResponse = mapper.map(updateUser, UserResponse.class);
        return userResponse;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("DELETE User By Id: {} from UserService", id);
    }
}
