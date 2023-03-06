package com.rest.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue createEventRequestQueue() {
        return new Queue("create-event-request", false);
    }
    @Bean
    public Queue updateEventRequestQueue() {
        return new Queue("update-event-request", false);
    }
    @Bean
    public Queue deleteEventRequestQueue() {
        return new Queue("delete-event-request", false);
    }

}
