package com.rest.app;

import com.rest.dto.Event;
import com.rest.api.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventServiceController {
    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("event/{id}")
    public Event getEvent(@PathVariable long id) {
        return eventService.getEvent(id);
    }

    @GetMapping("event")
    public List<Event> getAllEvents(@RequestParam(required = false) String title) {
        if (title != null) {
            return eventService.getAllEventsByTitle(title);
        } else {
            return eventService.getAllEvents();
        }
    }

    @PostMapping("event")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("event")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("event/{id}")
    public void deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
    }
}
