package org.authService.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.authService.dto.RoleDto;
import org.authService.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<UserDto> users;


    public Optional<UserDto> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}