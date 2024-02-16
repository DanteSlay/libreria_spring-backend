package com.javier.libreria_springbackend.security.auth;

import com.javier.libreria_springbackend.security.jwt.JwtService;
import com.javier.libreria_springbackend.models.user.IUserDao;
import com.javier.libreria_springbackend.models.user.Role;
import com.javier.libreria_springbackend.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Clase de servicio que maneja la lógica de autenticación.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /** El repositorio de acceso a datos para usuarios. */
    private final IUserDao userDao;

    /** El servicio de generación y validación de tokens JWT. */
    private final JwtService jwtService;

    /** El codificador de contraseñas. */
    private final PasswordEncoder passwordEncoder;

    /** El administrador de autenticación de Spring Security. */
    private final AuthenticationManager authenticationManager;

    /**
     * Realiza el proceso de autenticación de un usuario y genera un token JWT.
     * @param request La solicitud de inicio de sesión del usuario.
     * @return La respuesta de autenticación con el token generado y los roles del usuario.
     * @throws UsernameNotFoundException Si no se encuentra el usuario en el repositorio.
     */
    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario utilizando Spring Security.
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // Obtener los detalles del usuario autenticado desde el repositorio de usuarios.
        UserDetails userDetails = userDao.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // Generar un token JWT utilizando los detalles del usuario autenticado.
        String token = jwtService.getToken(userDetails);
        // Crear la respuesta de autenticación que incluye el token y los roles del usuario.
        return AuthResponse.builder()
                .token(token)
                .role(userDetails.getAuthorities())
                .build();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param request La solicitud de registro del usuario.
     * @return La respuesta de autenticación con el token generado para el nuevo usuario.
     */
    public AuthResponse registro(RegisterRequest request) {
        // Construir un nuevo objeto de usuario con los detalles proporcionados en la solicitud de registro.
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .role(Role.USER) // Asignar el rol de usuario por defecto al nuevo usuario.
                .build();

        // Guardar el nuevo usuario en el repositorio.
        userDao.save(user);

        // Generar un token JWT para el nuevo usuario.
        String token = jwtService.getToken(user);

        // Crear la respuesta de autenticación que incluye el token generado para el nuevo usuario.
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
