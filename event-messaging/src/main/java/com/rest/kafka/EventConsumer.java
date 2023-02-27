package com.rest.kafka;

import com.rest.api.EventService;
import com.rest.dto.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
//@Profile("kafka")
public class EventConsumer {
    private EventService eventService;

    @KafkaListener(topics = "create-event-request", groupId = "object", containerFactory = "eventListener")
    public void createEventObject(Event event) {
        System.out.println(event.toString());
    }

//    @KafkaListener(topics = "topicName", groupId = "foo")
//    public void createEvent(List<Event> events) {
//        events.forEach(e -> eventService.createEvent(e));
//    }
//
//    @KafkaListener
//    public void updateEvent(List<Event> events) {
//        events.forEach(e -> eventService.updateEvent(e));
//    }
//
//    @KafkaListener
//    public void deleteEvent(List<Identifier> eventIds) {
//        eventIds.forEach(e -> eventService.deleteEvent((Long)e.getValue()));
//    }

}

