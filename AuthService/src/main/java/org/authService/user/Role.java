package org.authService.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permission;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    public static Role fromString(String text) {
        for (Role role : Role.values()) {
            if (role.getAuthority().equalsIgnoreCase("ROLE_" + text)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
