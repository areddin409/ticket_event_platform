package com.areddin.tickets.filters;

import com.areddin.tickets.services.UserProvisioningService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Automatically provisions users from OAuth2/Keycloak JWT tokens.
 * Creates a new User entity on first login if it doesn't already exist.
 * Extracts user data from JWT claims (sub, preferred_username, email).
 */
@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserProvisioningService userProvisioningService;

    /**
     * Provisions a user from JWT token if it doesn't already exist in the database.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Retrieve the current authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Validate authentication: must exist, be authenticated, and have a JWT principal
        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof Jwt jwt) {

            // Extract Keycloak user ID from JWT subject claim
            UUID keycloakId = UUID.fromString(jwt.getSubject());

            userProvisioningService.provisionIfAbsent(
                    keycloakId,
                    jwt.getClaimAsString("preferred_username"),
                    jwt.getClaimAsString("email")
            );
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
