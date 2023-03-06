package com.rest.kafka;

import com.rest.api.EventMessaging;
import com.rest.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Profile("kafka")
public class EventProducer implements EventMessaging {
    @Autowired
    private KafkaTemplate<String, Event> eventKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Override
    public void createEvent(Event event) {
        sendMessageAsEvent("create-event-notification", event);
    }

    @Override
    public void updateEvent(Event event) {
        sendMessageAsEvent("update-event-notification", event);
    }

    @Override
    public void deleteEvent(Long id) {
        sendMessageAsString("delete-event-notification", "Event was deleted. Id = " + id);
    }

    private void sendMessageAsEvent(String topicName, Event message) {
        ListenableFuture<SendResult<String, Event>> future =
                eventKafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Event>>() {

            @Override
            public void onSuccess(SendResult<String, Event> result) {
                System.out.println("Sent message=[" + message.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    private void sendMessageAsString(String topicName, String message) {
        ListenableFuture<SendResult<String, String>> future =
                stringKafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}

