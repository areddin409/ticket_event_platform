package com.areddin.tickets.services;

import com.areddin.tickets.domain.entities.User;
import com.areddin.tickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProvisioningService {

    private final UserRepository userRepository;

    @Transactional
    public void provisionIfAbsent(UUID keycloakId, String username, String email) {
        if (userRepository.findById(keycloakId).isPresent()) {
            return;
        }

        User user = new User();
        user.setId(keycloakId);
        user.setName(username);
        user.setEmail(email);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ignored) {
            // Concurrent request already inserted this user.
        }
    }
}

