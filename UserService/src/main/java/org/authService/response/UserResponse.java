package org.authService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.authService.entity.Role;
import org.springframework.lang.NonNull;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private Role role;
}
