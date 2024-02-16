package com.javier.libreria_springbackend.security;

import com.javier.libreria_springbackend.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String RUTA_LIBRO_REST = "/libro/**";
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Deshabilita la protección contra CSRF
                .csrf(csrf -> csrf.disable())
                // Define las reglas de autorización para las peticiones HTTP
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                // Autoriza los métodos POST, PUT/PATCH y DELETE en las rutas "/libro/**" solo para usuarios con el rol "ADMIN"
                                .requestMatchers(HttpMethod.POST, RUTA_LIBRO_REST).hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, RUTA_LIBRO_REST).hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, RUTA_LIBRO_REST).hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, RUTA_LIBRO_REST).hasAuthority("ADMIN")
                                // Permite el acceso sin autenticación a las rutas "/auth/**" y "/h2-console/**"
                                .requestMatchers("/auth/**", "/h2-console/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                                // Exige autenticación para cualquier otra ruta
                                .anyRequest().authenticated()
                )
                // Configura la gestión de sesiones para que sean sin estado
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación personalizado
                .authenticationProvider(authProvider)
                // Añade el filtro de autenticación JWT antes del filtro de autenticación de nombre de usuario y contraseña
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
