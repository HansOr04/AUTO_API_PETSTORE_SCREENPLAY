package com.automation.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class CreatePet implements Performable {

    private final String name;
    private final String status;

    public CreatePet(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public static CreatePet withName(String name, String status) {
        return new CreatePet(name, status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String body = "{"
                + "\"id\": 0,"
                + "\"name\": \"" + name + "\","
                + "\"status\": \"" + status + "\""
                + "}";

        var response = SerenityRest.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .body(body)
                .when()
                .post("/pet")
                .then()
                .extract()
                .response();

        Serenity.setSessionVariable("lastResponse").to(response);
        long petId = response.jsonPath().getLong("id");
        Serenity.setSessionVariable("petId").to(petId);
    }
}
