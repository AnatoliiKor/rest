package com.rest.rabbit;

import com.rest.api.EventService;
import com.rest.dto.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class EventConsumer {
    @Autowired
    EventService eventService;

    @RabbitListener(queues = "create-event-request")
    public void createEvent(Event event) {
        eventService.createEvent(event);
    }

    @RabbitListener(queues = "update-event-request")
    public void updateEvent(Event event) {
        eventService.updateEvent(event);
    }

    @RabbitListener(queues = "delete-event-request")
    public void deleteEvent(Long eventId) {
        eventService.deleteEvent(eventId);
    }
}

