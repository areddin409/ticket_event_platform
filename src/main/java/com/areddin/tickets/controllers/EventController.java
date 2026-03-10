package com.areddin.tickets.controllers;

import com.areddin.tickets.domain.CreateEventRequest;
import com.areddin.tickets.domain.dtos.CreateEventRequestDto;
import com.areddin.tickets.domain.dtos.CreateEventResponseDto;
import com.areddin.tickets.domain.entities.Event;
import com.areddin.tickets.mappers.EventMapper;
import com.areddin.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<CreateEventResponseDto>> getEvents() {
        List<CreateEventResponseDto> events = eventService.listEvents().stream()
                .map(eventMapper::toDto)
                .toList();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto
    ) {
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = UUID.fromString(jwt.getSubject());

        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);

    }


}
