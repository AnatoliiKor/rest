package com.rest.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.EventService;
import com.rest.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
@Profile("kafka")
public class EventConsumer {
    @Autowired
    private EventService eventService;

    // Json JsonDeserializer returns List<LinkedHashMap> that need to convert into List<Event>. Here ObjectMapper is used
    @Autowired
    ObjectMapper mapper;


    @KafkaListener(topics = "create-event-request", groupId = "eventsREST", containerFactory = "eventListListener")
    public void createEvent(List<LinkedHashMap> events) {
        convertLinkedHashMapToEventList(events).forEach(e -> eventService.createEvent(e));
    }

    @KafkaListener(topics = "update-event-request", groupId = "eventsREST", containerFactory = "eventListListener")
    public void updateEvent(List<LinkedHashMap> events) {
        convertLinkedHashMapToEventList(events).forEach(e -> eventService.updateEvent(e));

    }

    // Identifier has no Creators, like default constructor, cannot deserialize from Object value
    @KafkaListener(topics = "delete-event-request", groupId = "eventsREST", containerFactory = "eventListListener")
    public void deleteEvent(List<LinkedHashMap> eventIds) {
//        mapper.convertValue(eventIds, new TypeReference<List<Identifier>>() { }).forEach(e -> eventService.deleteEvent((Long)e.getValue()));
        convertLinkedHashMapToEventList(eventIds).forEach(e -> eventService.deleteEvent(e.getId()));
    }

    private List<Event> convertLinkedHashMapToEventList(List<LinkedHashMap> maps) {
        return mapper.convertValue(maps, new TypeReference<List<Event>>() { });
    }
}

