package com.example.employeesmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin()
                .loginPage("/employees/login")
                .failureHandler((request, response, exception) -> {

                    String errorMessage = null;
                    int sessionTimeoutInMinutes = 1;
                    if (exception instanceof DisabledException) {
                        // 잠긴 계정의 경우 실패 처리 로직 구현
                        // 시간설정 오래 있을 필요조차 없음.
                        response.sendRedirect("/login/locked");
                    } else if (exception instanceof BadCredentialsException) {
                        errorMessage = "아이디 또는 비밀번호가 잘못되었습니다.";
                        request.getSession().setMaxInactiveInterval(sessionTimeoutInMinutes);
                        request.getSession().setAttribute("message", errorMessage);
                        response.sendRedirect("/employees/login");
                    } else {
                        errorMessage = "로그인에 실패하였습니다. 다시 시도해주세요.";
                        request.getSession().setMaxInactiveInterval(sessionTimeoutInMinutes);
                        request.getSession().setAttribute("message", errorMessage);
                        response.sendRedirect("/employees/login");
                    }
                })
                        .defaultSuccessUrl("/employees/signature");

        http.logout()
                .logoutUrl("/");
//                .logoutSuccessUrl("/employees/login");

        return http.build();

    }
}
