package com.rest.api;

import com.rest.dto.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Event event);

    Event getEvent(long id);

    void deleteEvent(long id);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);

}
