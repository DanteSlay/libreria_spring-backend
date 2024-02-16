package com.javier.libreria_springbackend.security.auth;

import com.javier.libreria_springbackend.models.user.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de la aplicación, que incluye la gestión de autenticación y la codificación de contraseñas.
 */
@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final IUserDao userDao;

    /**
     * Configura el administrador de autenticación de Spring Security.
     *
     * @param config La configuración de autenticación proporcionada por Spring.
     * @return El administrador de autenticación configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura el proveedor de autenticación de la aplicación.
     *
     * @return El proveedor de autenticación configurado.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Se crea un proveedor de autenticación basado en el repositorio de usuarios y la codificación de contraseñas
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService()); // Se establece el servicio de detalles de usuario
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Se establece el codificador de contraseñas
        return authenticationProvider;
    }

    /**
     * Configura el codificador de contraseñas para usar el algoritmo BCrypt.
     *
     * @return El codificador de contraseñas configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Se utiliza BCryptPasswordEncoder para codificar las contraseñas
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el servicio de detalles de usuario para obtener los detalles del usuario desde el repositorio de datos.
     *
     * @return El servicio de detalles de usuario configurado.
     */
    @Bean
    public UserDetailsService userDetailService() {
        // Se define el servicio de detalles de usuario que busca usuarios por nombre de usuario en el repositorio de datos
        return username -> userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
