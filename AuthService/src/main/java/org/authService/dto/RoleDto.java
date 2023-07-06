package org.authService.dto;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@RequiredArgsConstructor
public enum RoleDto implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");

    public static RoleDto fromString(String text) {
        for (RoleDto role : RoleDto.values()) {
            if (role.getAuthority().equalsIgnoreCase("ROLE_" + text)) {
                return role;
            }
        }
        return null;
    }

    RoleDto(String admin) {
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
