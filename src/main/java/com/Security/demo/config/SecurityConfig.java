package com.Security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Avisa o Spring Boot
    @EnableWebSecurity // Liga segurança Spring Web
    public class SecurityConfig {

        @Bean //Insere o objeto FilterChain nesse contexto
        public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/publico/**").permitAll()
                            .requestMatchers("/api/privada/**").hasRole("ADMIN")// Qualquer um entra
                            .anyRequest().authenticated() // Significa que o cara precisa estar logado para acessar a rota
                    )
                    .httpBasic(Customizer.withDefaults());   // ativa um login basico do navegador
            return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder encoder){

            //Usuario Padrão
            UserDetails user = User.builder()
                    .username("Junior")
                    .password(encoder.encode("senha123")) // Assim ele aplica o hash
                    .roles("USER") // Usuario normal
                    .build();

            //Usuario Chefe
            UserDetails admin = User.builder()
                    .username("Admin")
                    .password(encoder.encode("admin123"))
                    .roles("ADMIN", "USER")
                    .build();

            return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
}
