package com.rest.impl;

import com.rest.api.EventService;
import com.rest.dto.Event;
import com.rest.dto.EventRepository;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("This event (id=" + id + ") does not exist."));
    }

    @Override
    public String deleteEvent(long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return String.format("Event with id=%s was deleted", id);
        } else {
            throw new NoSuchElementException(String.format("Event with id=%s doesn't exist", id));
        }
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }
}
