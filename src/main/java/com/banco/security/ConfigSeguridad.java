package com.banco.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class ConfigSeguridad {

    // Configuración del servicio de usuarios en memoria
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails cliente = User.withUsername("cliente")
                .password(passwordEncoder.encode("cliente123"))
                .roles("USUARIO")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMINISTRADOR")
                .build();

        return new InMemoryUserDetailsManager(cliente, admin);
    }

    // Configuración del PasswordEncoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración de la seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitando CSRF para pruebas (no recomendado en producción)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/usuario/**").hasRole("USUARIO")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.defaultSuccessUrl("/home", true)); // Configuración de formLogin
        
        return http.build();
    }
}
