package com.javier.libreria_springbackend.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro para autenticación JWT.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /** Servicio JWT para la gestión de tokens. */
    private final JwtService jwtService;

    /** Servicio para cargar detalles de usuario. */
    private final UserDetailsService userDetailsService;

    /**
     * Filtra las solicitudes para autenticar con JWT.
     *
     * @param request Solicitud HTTP.
     * @param response Respuesta HTTP.
     * @param filterChain Cadena de filtros.
     * @throws ServletException Si ocurre una excepción de servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String username;
        final String token = getTokenFromRequest(request);

        // Si no se encuentra ningún token, continúa con la cadena de filtros.
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene el nombre de usuario del token JWT.
        username = jwtService.getUsernameFromToken(token);

        // Si se encuentra un nombre de usuario y no hay autenticación en el contexto de seguridad, autentica al usuario.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Si el token JWT es válido, establece la autenticación en el contexto de seguridad.
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continúa con la cadena de filtros.
        filterChain.doFilter(request, response);
    }

    /**
     * Obtiene el token JWT de la solicitud HTTP.
     *
     * @param request Solicitud HTTP.
     * @return Token JWT si está presente en la solicitud, de lo contrario, null.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Si el encabezado de autorización tiene un token JWT, lo devuelve.
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
