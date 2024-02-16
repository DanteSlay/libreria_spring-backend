package com.javier.libreria_springbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de inicio de sesión.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /** El nombre de usuario proporcionado en la solicitud. */
    private String username;

    /** La contraseña proporcionada en la solicitud. */
    private String password;

}
