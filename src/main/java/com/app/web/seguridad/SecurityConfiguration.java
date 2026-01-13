package com.app.web.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // LOGIN
                .requestMatchers(
                    "/",
                    "/login",
                    "/registro",
                    "/css/**",
                    "/js/**",
                    "/img/**"
                ).permitAll()

                // ADMIN
                .requestMatchers("/libros/**").hasRole("ADMIN")

                // USUARIO
                .requestMatchers("/usuario/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")
                .successHandler(new LoginSuccessHandler())

                .failureUrl("/?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/?logout")
                .permitAll()
            );

        return http.build();
    }
}
