package com.rest.app;

import com.rest.dto.Event;
import com.rest.api.EventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventServiceController {
    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("event/{id}")
    public Event getEvent(@PathVariable long id) {
        return eventService.getEvent(id);
    }
}
