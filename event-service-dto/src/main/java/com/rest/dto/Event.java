package com.rest.dto;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String place;

    @Column
    private String speaker;

    @Column
    @Enumerated
    private EventType eventType;

    @Column
    @NotNull
    private LocalDateTime dateTime;

    public Event(String title, String place, String speaker, String eventType, String dateTime) {
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.setEventType(eventType);
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

    public void setEventType(String eventType) {
        this.eventType = EventType.valueOf(eventType);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", speaker='" + speaker + '\'' +
                ", eventType=" + eventType +
                ", dateTime=" + dateTime +
                '}';
    }
}
