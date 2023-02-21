package com.rest.app;

import com.rest.api.EventService;
import com.rest.dto.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(com.rest.impl.Configuration.class)
public class Application implements CommandLineRunner {

	@Autowired
	private EventService eventService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		loadEventsAsStartup();
	}

	private void loadEventsAsStartup() {
		eventService.createEvent(new Event("Meeting with Client", "Meeting room", "Tom Henks", "MEETING", "2023-02-22T10:00:00"));
		eventService.createEvent(new Event("Meeting with Client", "Meeting room", "Tom Henks", "MEETING", "2023-02-26T12:00:00"));
		eventService.createEvent(new Event("Interview with guest", "Conference room", "Ivan Bogun", "INTERVIEW", "2023-02-23T10:00:00"));
		eventService.createEvent(new Event("Webinar about conversation", "Conference room", "John Brown", "WEBINAR", "2023-02-24T10:00:00"));
	}
}
