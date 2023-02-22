package com.rest.app;

import com.rest.dto.Event;
import com.rest.api.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/event")
public class EventServiceController {
    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable (value = "id") long id) {
        return eventService.getEvent(id);
    }

    @GetMapping()
    public Page<Event> getAllEvents(@RequestParam(required = false) String title, Pageable pageable) {
        if (title != null) {
            return eventService.getAllEventsByTitle(title, pageable);
        } else {
            return eventService.getAllEvents(pageable);
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable (value = "id") long id, @Valid @RequestBody Event event) {
        event.setId(id);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable (value = "id") long id) {
        return eventService.deleteEvent(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementExceptions(NoSuchElementException e) {
        return e.getMessage();
    }
}
