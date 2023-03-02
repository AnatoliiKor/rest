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
//@Profile("kafka")
public class EventProducer implements EventMessaging {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void createEvent(Event event) {
//        sendMessage("create-event-notification", "New event was created. Id = " + event.getId());
    }

    @Override
    public void updateEvent(Event event) {
        sendMessage("update-event-notification", "Event was updated. Id = " + event.getId());
    }

    @Override
    public void deleteEvent(Long id) {
        sendMessage("delete-event-notification", "Event was deleted. Id = " + id);
    }

    public void sendMessage(String topicName, String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

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
