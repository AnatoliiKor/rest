package com.rest.activemq;

import com.rest.api.EventService;
import com.rest.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
public class EventConsumer {

    @Autowired
    EventService eventService;

    @JmsListener(destination = "create-event-request")
    public void createEvent(Event event) {
        eventService.createEvent(event);

    }

    @JmsListener(destination = "update-event-request")
    public void updateEvent(Event event) {
        eventService.updateEvent(event);
    }

    @JmsListener(destination = "delete-event-request",
            containerFactory = "defaultMessageConvertorUsedForDefaultJmsListenerContainerFactory")
    public void deleteEvent(Long eventId) {
        eventService.deleteEvent(eventId);
    }
}

