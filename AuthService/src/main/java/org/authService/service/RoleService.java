package org.authService.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authService.entity.Role;
import org.authService.repository.RoleRepository;
import org.authService.response.RoleResponse;
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
public class RoleService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WebClient webClient;

    public RoleResponse findRoleByUserId(Long userId){
        Optional<Role> roleByUserId = roleRepository.findRoleByUserId(userId);
        RoleResponse roleResponse = mapper.map(roleByUserId, RoleResponse.class);
        UserResponse userResponse = webClient.get().uri("/user/" + userId)
                .retrieve().bodyToMono(UserResponse.class).block();
        roleResponse.setUserResponse(userResponse);
        return roleResponse;
    }
}
