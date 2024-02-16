package com.javier.libreria_springbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Clase que representa la respuesta de autenticación enviada al cliente.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    /** El token de autenticación generado para el usuario. */
    private String token;

    /** La colección de roles asignados al usuario autenticado. */
    private Collection<? extends GrantedAuthority> role;
}

