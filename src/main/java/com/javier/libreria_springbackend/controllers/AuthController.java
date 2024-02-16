package com.javier.libreria_springbackend.controllers;

import com.javier.libreria_springbackend.security.auth.AuthResponse;
import com.javier.libreria_springbackend.security.auth.AuthService;
import com.javier.libreria_springbackend.security.auth.LoginRequest;
import com.javier.libreria_springbackend.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de gestionar las operaciones de autenticación y registro de usuarios.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Maneja la solicitud de inicio de sesión de un usuario.
     *
     * @param request La solicitud de inicio de sesión que contiene las credenciales del usuario.
     * @return Una respuesta HTTP con los detalles de autenticación si el inicio de sesión es exitoso.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Invoca el servicio de autenticación para procesar la solicitud de inicio de sesión y devuelve la respuesta
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Maneja la solicitud de registro de un nuevo usuario.
     *
     * @param request La solicitud de registro que contiene los datos del nuevo usuario.
     * @return Una respuesta HTTP con los detalles de autenticación si el registro es exitoso.
     */
    @PostMapping(value = "registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody RegisterRequest request) {
        // Invoca el servicio de autenticación para procesar la solicitud de registro y devuelve la respuesta
        return ResponseEntity.ok(authService.registro(request));
    }
}
