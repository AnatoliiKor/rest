package com.rest.api;

import com.rest.dto.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Event event);

    Event getEvent(long id);

    String deleteEvent(long id);

    Page<Event> getAllEvents(Pageable pageable);

    List<Event> getAllEventsByTitle(String title);

}
