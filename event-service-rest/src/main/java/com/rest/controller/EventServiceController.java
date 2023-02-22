package com.rest.controller;

import com.rest.api.EventService;
import com.rest.dto.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(path = "/events")
@Tag(name = "Events", description = "The Events API")
public class EventServiceController {
    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find the event by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Event not found")})
    public Event getEvent(@PathVariable(value = "id") long id) {
        return eventService.getEvent(id);
    }

    @GetMapping()
    @Operation(summary = "Find all events")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventService.getAllEvents(pageable);

    }

    @GetMapping("/title")
    @Operation(summary = "Find all events for specified title")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<Event> getAllEventsForTitle(@RequestParam String title) {
        return eventService.getAllEventsByTitle(title);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an event")
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an event")
    public Event updateEvent(@PathVariable(value = "id") long id, @Valid @RequestBody Event event) {
        event.setId(id);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event")
    public String deleteEvent(@PathVariable(value = "id") long id) {
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
