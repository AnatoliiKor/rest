package com.rest.api;

import com.rest.dto.Event;
import com.rest.dto.EventType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    Event createEvent(String title, String place, String speaker, EventType eventType, String dateTime);

    Event updateEvent(Event event);

    Event getEvent(long id);

    Event deleteEvent(long id);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle();

}
