package com.rest.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column
    private String place;

    @Column
    private String speaker;

    @Column
    @Enumerated
    private EventType eventType;

    @Column
    private LocalDateTime dateTime;

    public Event(String title, String place, String speaker, EventType eventType, String dateTime) {
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.setDateTime(dateTime);
    }

    protected Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime);
    }
}
