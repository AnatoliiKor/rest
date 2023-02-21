package com.rest.app;

import com.rest.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.io.IOException;

@SpringBootApplication
@Import(com.rest.impl.Configuration.class)
public class Application implements CommandLineRunner {

	@Autowired
	private EventService eventService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadEventsAsStartup();
	}

	private void loadEventsAsStartup() throws IOException {

	}
}
