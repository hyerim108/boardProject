package com.project.board.auth.config;

import com.project.board.Domain.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf((csrfConfig)->csrfConfig.disable())
                .headers((headerConfig)-> headerConfig.frameOptions(
                        frameOptionsConfig -> frameOptionsConfig.disable()
                ))
                .authorizeHttpRequests((authorizeRequest)-> authorizeRequest
//                        .requestMatchers("/api/v1/**").hasRole(Role.USER.)
                        .requestMatchers("/","/css/**","images/**","js/**","login/*","/logout/*").permitAll()
                        .anyRequest().authenticated())
                .logout((logoutConfig)->logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
