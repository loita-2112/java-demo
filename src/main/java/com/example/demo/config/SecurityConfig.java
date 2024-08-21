package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll() // Cho phép tất cả các yêu cầu mà không cần xác thực
                .and()
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/employee/**") // Bỏ qua CSRF cho tất cả các yêu cầu đến endpoint /employee
                );

        return http.build();
    }

}