package org.authService.user;


import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
@RequiredArgsConstructor
public enum RoleDto
//        implements GrantedAuthority
{
    USER,
    ADMIN;

//    public static RoleDto fromString(String text) {
//        for (RoleDto role : RoleDto.values()) {
//            if (role.getAuthority().equalsIgnoreCase("ROLE_" + text)) {
//                return role;
//            }
//        }
//        return null;
//    }
//
////    @Override
////    public String getAuthority() {
////        return "ROLE_" + name();
////    }
}
