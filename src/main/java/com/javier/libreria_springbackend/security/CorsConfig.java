package com.javier.libreria_springbackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Permitimos cualquier origen y todos los metodos
                .allowedOrigins("*")
                .allowedMethods("*");

/*
                Ejemplo:

                .allowedOrigins("http://example.com") // Restringir a un origen específico
                .allowedMethods("GET", "POST"); // Permitir solo los métodos GET y POST
*/

    }
}
