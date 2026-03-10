package com.areddin.tickets.services;

import com.areddin.tickets.domain.entities.User;
import com.areddin.tickets.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProvisioningServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserProvisioningService userProvisioningService;

    @Test
    void provisionIfAbsent_doesNothingWhenUserExists() {
        UUID keycloakId = UUID.randomUUID();
        when(userRepository.findById(keycloakId)).thenReturn(Optional.of(new User()));

        userProvisioningService.provisionIfAbsent(keycloakId, "andrew", "andrew@example.com");

        verify(userRepository, never()).save(org.mockito.ArgumentMatchers.any(User.class));
    }

    @Test
    void provisionIfAbsent_savesWhenUserDoesNotExist() {
        UUID keycloakId = UUID.randomUUID();
        when(userRepository.findById(keycloakId)).thenReturn(Optional.empty());

        userProvisioningService.provisionIfAbsent(keycloakId, "andrew", "andrew@example.com");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User saved = userCaptor.getValue();
        assertEquals(keycloakId, saved.getId());
        assertEquals("andrew", saved.getName());
        assertEquals("andrew@example.com", saved.getEmail());
    }

    @Test
    void provisionIfAbsent_ignoresDuplicateInsertRace() {
        UUID keycloakId = UUID.randomUUID();
        when(userRepository.findById(keycloakId)).thenReturn(Optional.empty());
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class)))
                .thenThrow(new DataIntegrityViolationException("duplicate key"));

        assertDoesNotThrow(() -> userProvisioningService.provisionIfAbsent(keycloakId, "andrew", "andrew@example.com"));
    }
}

