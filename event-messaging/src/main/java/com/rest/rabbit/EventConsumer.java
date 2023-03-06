package com.rest.rabbit;

import com.rest.dto.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.data.relational.core.mapping.event.Identifier;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class EventConsumer {
    public EventConsumer() {
        System.out.println("dfgdf");
    }
    @RabbitListener
    public void createEvent(Event event) {

    }

    @RabbitListener
    public void updateEvent(Event event) {

    }

    @RabbitListener
    public void deleteEvent(Identifier eventId) {

    }
}

