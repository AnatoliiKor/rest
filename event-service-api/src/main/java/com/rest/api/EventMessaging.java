package com.rest.api;

import com.rest.dto.Event;

public interface EventMessaging {
    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Long id);

}
