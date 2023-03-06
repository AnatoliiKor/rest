package com.rest.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestTest {

    @Test
    public void whenSendRequestToEventsResource_thenOK() {
        Response response = RestAssured.get("http://localhost:8080/events");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void eventsResourceReturnsOKWithExpectedId() {
        when().
                get("/events/{id}", 2).
                then().
                statusCode(200).
                body("id", equalTo(2),
                        "place", equalTo("Meeting room"));

    }
}
