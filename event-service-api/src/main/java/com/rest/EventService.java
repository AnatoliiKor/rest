package com.rest;

import java.util.List;

public interface EventService {

    Event createEvent();

    Event updateEvent();

    Event getEvent();

    Event deleteEvent();

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle();

}
