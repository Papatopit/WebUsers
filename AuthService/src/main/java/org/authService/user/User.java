package org.authService.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.authService.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

    public class User implements UserDetails {

        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @OneToMany(mappedBy = "user")
        private List<Token> tokens;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(role);
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }