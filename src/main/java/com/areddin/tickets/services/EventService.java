package com.areddin.tickets.services;

import com.areddin.tickets.domain.CreateEventRequest;
import com.areddin.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    /**
 * Create a new Event for the specified organizer.
 *
 * @param organizerId UUID of the organizer who will own the event
 * @param event details required to create the event
 * @return the created Event
 */
Event createEvent(UUID organizerId, CreateEventRequest event);
}
