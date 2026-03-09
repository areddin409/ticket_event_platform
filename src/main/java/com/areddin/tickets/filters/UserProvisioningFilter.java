package com.areddin.tickets.filters;

import com.areddin.tickets.domain.entities.User;
import com.areddin.tickets.repositories.UserRepository;
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

    private final UserRepository userRepository;

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

            // Check if user already exists in the database
            if (!userRepository.existsById(keycloakId)) {

                // Create new user entity
                User user = new User();
                user.setId(keycloakId);
                user.setName(jwt.getClaimAsString("preferred_username"));
                user.setEmail(jwt.getClaimAsString("email"));

                // Persist user to database
                userRepository.save(user);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
