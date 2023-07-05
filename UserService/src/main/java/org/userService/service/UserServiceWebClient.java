package org.userService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.userService.configuration.MapperConfig;
import org.userService.entity.Role;
import org.userService.entity.User;
import org.userService.exception.UserNotFoundException;
import org.userService.repository.UserRepository;
import org.userService.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceWebClient {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperConfig mapperConfig;

    public UserResponse getUserById(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> throwException(String.valueOf(id))));
        log.info("get User By Id from UserService:  {}", user);
        UserResponse userResponse = mapperConfig.modelMapperBean().map(user, UserResponse.class);
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
        updateUser.setId(id);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setBirthday(user.getBirthday());
        updateUser.setRole(user.getRole());
        updateUser.setPassword(user.getPassword());
        userRepository.save(updateUser);
        UserResponse userResponse = mapperConfig.modelMapperBean().map(updateUser, UserResponse.class);
        return userResponse;
    }

    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            log.info("DELETE User By Id: {} from UserService", id);
            return true;
        }else {
            throwException(String.valueOf(id));
            return false;
        }
    }

    public UserResponse changeRoleForUser(Long id, Role role) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setRole(role);
        UserResponse userResponse = mapperConfig.modelMapperBean().map(updateUser, UserResponse.class);
        return userResponse;
    }

    private UserNotFoundException throwException(String value) {
        throw new UserNotFoundException("User Not Found with ID: " + value);
    }

}
