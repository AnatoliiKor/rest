package com.rest.activemq;

import com.rest.api.EventMessaging;
import com.rest.dto.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
public class EventProducer implements EventMessaging {

    private final JmsTemplate jmsTemplate;

    public EventProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void createEvent(Event event) {
        jmsTemplate.convertAndSend("create-event-notification", event);
    }

    @Override
    public void updateEvent(Event event) {
        jmsTemplate.convertAndSend("update-event-notification", event);
    }

    @Override
    public void deleteEvent(Long id) {
        jmsTemplate.convertAndSend("delete-event-notification", "Event was deleted. Id = " + id);
    }
}
