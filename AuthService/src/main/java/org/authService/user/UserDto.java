package org.authService.user;

import lombok.*;

import java.util.Date;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

//import java.util.Collection;
//import java.util.Collections;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
//        implements UserDetails
{
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private String password;
    private RoleDto role;



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(role);
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}