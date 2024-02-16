package com.javier.libreria_springbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de registro de usuario.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /** El nombre de usuario proporcionado en la solicitud. */
    private String username;

    /** El nombre del usuario proporcionado en la solicitud. */
    private String nombre;

    /** La contraseña proporcionada en la solicitud. */
    private String password;
}
