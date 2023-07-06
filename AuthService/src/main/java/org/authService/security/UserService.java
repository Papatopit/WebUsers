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

    public UserService() {
        this.users = List.of(
                new UserDto("admin", "Pablo", "Pablito",
                        "pablito@mail.ru", "16.12.87", "1234",
                        Collections.singleton(RoleDto.ADMIN)),

                new UserDto("user", "Vito", "Genovese",
                        "Genovese@mail.ru", "08.08.56", "1234",
                        Collections.singleton(RoleDto.USER)));
    }


    public Optional<UserDto> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}