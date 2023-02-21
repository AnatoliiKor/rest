package com.rest.impl;

import com.rest.api.EventService;
import com.rest.dto.Event;
import com.rest.dto.EventRepository;
import com.rest.dto.EventType;
import com.rest.impl.exception.EventDoesNotExistException;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Import(com.rest.dto.Configuration.class)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventDoesNotExistException(id));
    }

    @Override
    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }
}
