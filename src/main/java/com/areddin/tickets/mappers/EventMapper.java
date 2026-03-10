package com.areddin.tickets.mappers;

import com.areddin.tickets.domain.CreateEventRequest;
import com.areddin.tickets.domain.CreateTicketTypeRequest;
import com.areddin.tickets.domain.dtos.CreateEventRequestDto;
import com.areddin.tickets.domain.dtos.CreateEventResponseDto;
import com.areddin.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.areddin.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

}
