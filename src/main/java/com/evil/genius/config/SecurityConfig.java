package com.evil.genius.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").anonymous()
                        // Alumno
                        .requestMatchers(HttpMethod.GET, "/api/notas/alumno/**").hasRole("ALUMNO")
                        .requestMatchers(HttpMethod.GET, "/api/cursos/alumno/**").hasRole("ALUMNO")
                        .requestMatchers(HttpMethod.GET, "/api/profesor/alumno/**").hasRole("ALUMNO")
                        // Profesor
                        .requestMatchers("/api/cursos/profesor/**").hasRole("Profesor")
                        .requestMatchers("/api/notas/profesor/**").hasRole("Profesor")
                        // ADMIN
                        .requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
