package com.kiosquitodo.backend.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF para pruebas (solo si no usás formularios)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/productos/**").permitAll() // ✅ permite acceso libre
                .anyRequest().authenticated() // el resto requiere auth
            )
            .httpBasic(); // O podés usar .formLogin() si querés probar login con formulario

        return http.build();
    }
}
