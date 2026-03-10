package com.areddin.tickets.services;

import com.areddin.tickets.domain.CreateEventRequest;
import com.areddin.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
