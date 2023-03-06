package com.rest.rabbit;

import com.rest.api.EventMessaging;
import com.rest.dto.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class EventProducer implements EventMessaging {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void createEvent(Event event) {
        rabbitTemplate.convertAndSend("create-event-notification", event.toString());
    }

    @Override
    public void updateEvent(Event event) {
        rabbitTemplate.convertAndSend("update-event-notification", event.toString());
    }

    @Override
    public void deleteEvent(Long id) {
        rabbitTemplate.convertAndSend("delete-event-notification", "Event was deleted. Id = " + id);
    }
}
