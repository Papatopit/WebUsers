package org.authService.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


   @Bean
    public void configureSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()


                // Доступ только для не зарегистрированных пользователей
                .requestMatchers("/login").permitAll()
                .requestMatchers("/registration").not().authenticated()
                // Доступ только для пользователей с ролью ADMIN
                .requestMatchers("/admin" + "/**").hasRole("ADMIN")
                // Доступ только для пользователей с ролью USER
                .requestMatchers("/user" + "/**").hasRole("USER")
                .requestMatchers("/version").permitAll()
                // Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
//                .and()
//                // Настройка для входа в систему
//                .formLogin()
//                .loginPage("/login")
//
//
//
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//
//
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .anonymous()
//                .csrf().disable()
//                .cors()
//                .anonymous()
        ;
    }
}
