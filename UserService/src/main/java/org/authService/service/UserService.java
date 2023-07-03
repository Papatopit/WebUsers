package org.authService.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authService.entity.Role;
import org.authService.entity.User;
import org.authService.repository.UserRepository;
import org.authService.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private WebClient webClient;

    public UserResponse getUserBuId(Long id){
        Optional<User> user = userRepository.findById(id);
        log.info("get User By Id from UserService:  {}",user);
        UserResponse userResponse= mapper.map(user, UserResponse.class);
        return userResponse;
    }
    public UserResponse findRoleByUserId(Long userId){
        Optional<Role> roleByUserId = userRepository.findRoleByUserId(userId);
        UserResponse userResponse = webClient.get().uri("/user/" + userId)
                .retrieve().bodyToMono(UserResponse.class).block();

        return userResponse;
    }

    public UserResponse createUser(UserResponse userResponse){
       return null;
    }

}
